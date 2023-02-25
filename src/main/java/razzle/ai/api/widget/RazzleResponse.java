package razzle.ai.api.widget;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * created by Julian Duru on 25/02/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RazzleResponse {


    private String clientId;

    private RazzleWidget ui;

    private Map<String, String> addToContext;

    private String[] removeKeysFromContext;

    private Map<String, String> extraData;


    public static RazzleResponse of(RazzleWidget ui) {
        return RazzleResponse.builder()
                .ui(ui)
                .build();
    }


}


