package de.seuhd.campuscoffee.api.dtos;

import lombok.Builder;
import org.jspecify.annotations.Nullable;

import java.time.LocalDateTime;

/**
 * DTO record for POS metadata.
 */
@Builder(toBuilder = true)
public record ReviewDto (
        @Nullable Long id,
        @Nullable LocalDateTime createdAt, // is null when using DTO to create a new POS
        @Nullable LocalDateTime updatedAt, // is set when creating or updating a POS
        // TODO: Implement ReviewDto
        @Nonnull Long posId,
        @Nonnull Long userId,
        @NotBlank(message = "Review cannot be empty.")
        @Nonnull String review,
        @Min(value = 0, message = "Approval count must be greater than or equal to 0.")
        @Nonnull Integer approvalCount,
        @Nonnull Boolean approved


) implements Dto<Long> {
    @Override
    public @Nullable Long getId() {
        return id;
    }
}
