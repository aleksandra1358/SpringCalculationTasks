package com.globallogic.ultimateCalculationTool;

import com.globallogic.ultimateCalculationTool.task.TaskDBImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestTests
{
    private static final String USERNAME = "user";
    private static final String PASSWORD = "password";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testUnsecuredExampleEndpoint()
    {
        String body = this.restTemplate.getForObject("/test", String.class);
        assertThat(body).isEqualTo("Hello World");
    }

    @Test
    public void testTaskRetrieving() throws IOException, JSONException
    {
        this.restTemplate.postForObject("/tasks", "Desc", String.class);
        String body = this.restTemplate.getForObject("/tasks", String.class);
        assertNotNull(body);
        TaskDBImpl task = new TaskDBImpl("Desc");
        assertNotNull(task);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting().serializeNulls();
        Gson gson = builder.create();

        Type collectionType = new TypeToken<Collection<TaskDBImpl>>() {}.getType();
        Collection<TaskDBImpl> tasks = gson.fromJson(body, collectionType);
        assertNotNull(tasks);

        ResponseEntity<String> entity = this.restTemplate.getForEntity("/tasks", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testGetUnsecuredEndpoint()
    {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/tasks", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void testGetSecuredEndpoint()
    {
        ResponseEntity<String> entity = this.restTemplate.getForEntity("/results", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, entity.getStatusCode());
    }

    @Test
    public void testGetSecuredEndpointSuccess()
    {
        ResponseEntity<String> entity = this.restTemplate.withBasicAuth(USERNAME, PASSWORD).getForEntity("/results", String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }
}