package com.anz.platform.base;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.anz.platform.model.Users;
import com.anz.platform.util.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;

public abstract class BaseTest {
  protected static final String PATH_ORDERS_JSON = "/files/Orders.json";

  protected List<Users> prepareOrders() throws URISyntaxException, IOException {
    final String ordersJson = getMockContent(PATH_ORDERS_JSON);
    List<Users> orders = JsonUtils.toObject(ordersJson, new TypeReference<List<Users>>() {});
    return orders;
  }

  protected Context createContext() {
    TestContext context = new TestContext();
    context.setFunctionName("anz-platform-report");
    return context;
  }

  protected String getMockContent(final String path) throws URISyntaxException, IOException {
    URI respURI = ClassLoader.class.getResource(path).toURI();
    return new String(Files.readAllBytes(Paths.get(respURI)));
  }
}
