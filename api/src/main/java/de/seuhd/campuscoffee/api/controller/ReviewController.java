package de.seuhd.campuscoffee.api.controller;

import de.seuhd.campuscoffee.api.dtos.ReviewDto;
import de.seuhd.campuscoffee.api.mapper.DtoMapper;
import de.seuhd.campuscoffee.api.openapi.CrudOperation;
import de.seuhd.campuscoffee.domain.model.objects.Review;
import de.seuhd.campuscoffee.domain.ports.api.CrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.seuhd.campuscoffee.api.openapi.Operation.*;
import static de.seuhd.campuscoffee.api.openapi.Resource.REVIEW;

/**
 * Controller for handling reviews for POS, authored by users.
 */
@Tag(name="Reviews", description="Operations for managing reviews for points of sale.")
@Controller
@RequestMapping("/api/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController extends CrudController<Review, ReviewDto, Long> {

    // TODO: Correctly implement the service() and mapper() methods. Note the IntelliJ warning resulting from the @NonNull annotation.
    private final ReviewService reviewService;
    private final ReviewDtoMapper reviewDtoMapper;

    @Override
    protected @NonNull CrudService<Review, Long> service() {
        return reviewService;
    }

    @Override
    protected @NonNull DtoMapper<Review, ReviewDto> mapper() {
        return reviewDtoMapper;
    }

    @Operation
    @CrudOperation(operation=GET_ALL, resource=REVIEW)
    @GetMapping("")
    public @NonNull ResponseEntity<List<ReviewDto>> getAll() {
        return super.getAll();
    }

    // TODO: Implement the missing methods/endpoints.


    @Operation
    @CrudOperation(operation=GET_BY_ID, resource=REVIEW)
    @GetMapping("/{id}")
    public @NonNull ResponseEntity<ReviewDto> getById(
            @Parameter(description="Unique identifier of the REVIEW to retrieve.", required=true)
            @PathVariable Long id) {
        return super.getById(id);
    }

    @Operation
    @CrudOperation(operation=CREATE, resource=REVIEW)
    @PostMapping("")
    public @NonNull ResponseEntity<ReviewDto> create(
            @Parameter(description="Data of the REVIEW to create.", required=true)
            @RequestBody @Valid ReviewDto reviewDto) {
        return super.create(reviewDto);
    }

    @Operation
    @CrudOperation(operation=UPDATE, resource=REVIEW)
    @PutMapping("/{id}")
    public @NonNull ResponseEntity<ReviewDto> update(
            @Parameter(description="Unique identifier of the REVIEW to update.", required=true)
            @PathVariable Long id,
            @Parameter(description="Data of the REVIEW to update.", required=true)
            @RequestBody @Valid ReviewDto reviewDto) {
        return super.update(id, reviewDto);
    }

    @Operation
    @CrudOperation(operation=DELETE, resource=REVIEW)
    @DeleteMapping("/{id}")
    public @NonNull ResponseEntity<Void> delete(
            @Parameter(description="Unique identifier of the REVIEW to delete.", required=true)
            @PathVariable Long id) {
        return super.delete(id);
    }

    @Operation
    @CrudOperation(operation=FILTER, resource=REVIEW)
    @GetMapping("/filter")
    public ResponseEntity <List<ReviewDto>> filter(
            @Parameter(description="PosId of the REVIEW to retrieve.", required=true)
            @RequestParam("posId") Long posId,
            @Parameter(description="Approve of the REVIEW to retrieve.", required=true)
            @RequestParam("approved") Boolean approved) {
        List<Review> filteredReviews = reviewService.filter(posId, approved);
        
        //konvertiert die liste in einzelnde objekte
        List<ReviewDto> reviewDtos = filteredReviews.stream()
                .map(review -> reviewDtoMapper.fromDomain(review)).toList();

        return ResponseEntity.ok(reviewDtos);
    }
        
}
