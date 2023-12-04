package org.example;

import org.example.Screen.SideNavbar;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public Main() throws IOException, InterruptedException {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
      new SideNavbar().setVisible(true);
//         final String API_URL = "http://localhost:8080/attendance/defaulters?spreadsheetId=1NxDH80Y9dwKWR6HuHeeRiPB8JGD1J-yFeplxG-FeioE&classId=JSLD1";
//
//        HttpRequest request  = HttpRequest.newBuilder(URI.create(API_URL)).GET().build();
//
//        HttpClient client = HttpClient.newBuilder().build();
//
//        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println(response.statusCode());
//        System.out.println(response.body());

    }


}