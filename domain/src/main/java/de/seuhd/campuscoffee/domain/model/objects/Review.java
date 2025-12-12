package de.seuhd.campuscoffee.domain.model.objects;

import de.seuhd.campuscoffee.domain.model.DomainModel;
import lombok.Builder;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import java.time.LocalDateTime;

/**
 * Domain record that stores a review for a point of sale.
 * Reviews are approved once they received a configurable number of approvals.
 */
@Builder(toBuilder = true)
public record Review(
        @Nullable Long id, // null when the review has not been created yet
        //TODO: Implement review domain model.
        @NonNull Pos pos,
        @NonNull User author,
        @NonNull String text,
        @NonNull Integer rating,
        @NonNull Integer approvalCount
) implements DomainModel<Long> {
    @Override
    public Long getId() {
        return id;
    }
}