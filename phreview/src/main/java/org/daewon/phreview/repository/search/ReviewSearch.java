package org.daewon.phreview.repository.search;

import org.springframework.data.domain.Page;
import org.daewon.phreview.dto.ReviewListAllDTO;

public interface ReviewSearch {

    Page<ReviewListAllDTO> searchWithAll(String[] types, String keyword);

}
