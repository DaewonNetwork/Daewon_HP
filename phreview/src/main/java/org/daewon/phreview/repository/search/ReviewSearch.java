package org.daewon.phreview.repository.search;

import org.daewon.phreview.domain.Review;
import org.daewon.phreview.dto.ReviewListAllDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewSearch {

    Page<Review> search1(Pageable pageable);

    Page<Review> searchAll(String[] types, String keyword, Pageable pageable);

    Page<ReviewListAllDTO> searchWithAll(String[] types, String keyword, Pageable pageable);

}
