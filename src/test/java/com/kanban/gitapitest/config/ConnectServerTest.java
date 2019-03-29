package com.kanban.gitapitest.config;

import okhttp3.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.IOException;

import static javafx.scene.input.KeyCode.R;

@RunWith(SpringRunner.class)
public class ConnectServerTest {

    private Http3Connector connector;

    public ConnectServerTest() {
        this.connector = new Http3Connector();
    }

    @Test
    public void httpGetConnect() throws IOException {
        String response = connector.get("https://api.github.com/repos/dev-stream/test/pulls/1/comments");
        System.out.println(response);
    }
/*
    @Test
    public void httpPostConnect() {
        String response = connector.post("https://api.github.com/repos/dev-stream/test/pulls/1/comments")
    }*/
}