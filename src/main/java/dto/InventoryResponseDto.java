package dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDto {
    private Integer sold;
    @JsonAlias("new")
    private Integer newCount;
    private Integer string;
    private Integer unavailable;
    private Integer pending;
    private Integer available;
    private Integer disponivel;
    private Integer peric;
}
