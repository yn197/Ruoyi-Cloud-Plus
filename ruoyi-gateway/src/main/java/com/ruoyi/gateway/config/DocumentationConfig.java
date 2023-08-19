//package com.ruoyi.gateway.config;
//
///**
// * @author nisang
// * 2023/8/18 17:46
// * @version 1.0
// * Ruoyi-Cloud-Plus开发小组
// */
//import org.springframework.cloud.gateway.config.GatewayProperties;
//import org.springframework.cloud.gateway.route.RouteLocator;
//import org.springframework.cloud.gateway.support.NameUtils;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@Primary  //代表是Swagger主 配置
//public class DocumentationConfig implements SwaggerResourcesProvider {
//
//    public static final String API_URI = "v2/api-docs";
//    private final RouteLocator routeLocator;
//    private final GatewayProperties gatewayProperties;
//
//    public DocumentationConfig(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
//        this.routeLocator = routeLocator;
//        this.gatewayProperties = gatewayProperties;
//    }
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<String> routes = new ArrayList<>();
//        routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
//        gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId())).forEach(route -> {
//            route.getPredicates().stream()
//                    .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
//                    .forEach(predicateDefinition -> resources.add(swaggerResource(route.getId(),
//                            predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
//                                    .replace("**", API_URI))));
//        });
//        return resources;
//    }
//
//    private SwaggerResource swaggerResource(String name, String location) {
//        SwaggerResource swaggerResource = new SwaggerResource();
//        swaggerResource.setName(name);
//        swaggerResource.setLocation(location);
//        swaggerResource.setSwaggerVersion("1.0");
//        return swaggerResource;
//    }
//}
//
