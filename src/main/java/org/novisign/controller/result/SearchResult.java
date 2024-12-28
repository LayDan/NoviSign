package org.novisign.controller.result;

import java.util.Set;

public record SearchResult(Set<Long> images, Set<Long> slideshows) {
}
