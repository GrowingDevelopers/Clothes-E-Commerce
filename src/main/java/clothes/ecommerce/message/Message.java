package clothes.ecommerce.message;

import lombok.Data;

@Data
public class Message {
    private String message;
    private Object data;

    public Message() {
        this.data = null;
        this.message = null;
    }
}
