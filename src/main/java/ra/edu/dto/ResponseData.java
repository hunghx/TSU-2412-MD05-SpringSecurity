package ra.edu.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ResponseData {
    private boolean success;
    private  String message;
    private Object data;
    private Object error;
}
