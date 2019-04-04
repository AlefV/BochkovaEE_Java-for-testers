package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

public class RestTests2 {

    @Test
    public void getIssue() throws IOException {
        int id = 1000;
        String json = getExecutor().execute(Request.Get("http://bugify.stqa.ru/api/issues/"+id +".json")).returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues");
        Set<Issue> issuesNew = new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
        Issue issue = issuesNew.iterator().next();
        System.out.println(issue);
    }

    private Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
