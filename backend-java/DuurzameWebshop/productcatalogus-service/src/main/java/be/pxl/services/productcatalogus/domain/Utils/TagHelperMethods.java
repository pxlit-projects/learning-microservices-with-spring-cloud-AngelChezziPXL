package be.pxl.services.productcatalogus.domain.Utils;

import be.pxl.services.productcatalogus.domain.Tag;
import be.pxl.services.productcatalogus.domain.dto.TagRecord;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagHelperMethods {
    public static TagRecord mapTagToTagRecord(Tag tag) {
        return new TagRecord(tag.getId(), tag.getText());
    }

    /**
     * Converts a comma-separated string of tags into a list of individual strings.
     *
     * This method splits the input string by commas, trims any surrounding whitespace
     * from each resulting tag, filters out empty strings, and collects the non-empty
     * tags into a list.
     *
     * Example:
     * Input: " tag1, tag2 , , tag3 "
     * Output: ["tag1", "tag2", "tag3"]
     *
     * @param tags the input string containing comma-separated tags (e.g., "tag1, tag2, tag3")
     * @return a list of trimmed, non-empty tag strings
     */
    public static List<String> convertInputStringToListOfStrings(String tags) {
        tags = tags.toLowerCase();
        return Arrays.stream(tags.split(","))     // Split string by commas into stream
                .map(String::trim)                // Trim white space for each tag
                .filter(tag -> !tag.isEmpty())    // Filter out empty tags
                .collect(Collectors.toList());    // Collect the result into a list
    }
}
