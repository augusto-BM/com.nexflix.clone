package com.nexflix.clone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoStatsResponse {
    private long totalVideos, publishedVideos, totalDuration;
}
