package razzle.ai.java;

import lombok.Data;

import java.util.Map;

/**
 * created by julian on 09/02/2023
 */
@Data
public class CallDetails {


    private String userId;


    private String accountId;


    private String workspaceId;


    private Map<String, ?> context;


}

