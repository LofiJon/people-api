package one.digitalInnovation.personapi.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
	private String message;
}
