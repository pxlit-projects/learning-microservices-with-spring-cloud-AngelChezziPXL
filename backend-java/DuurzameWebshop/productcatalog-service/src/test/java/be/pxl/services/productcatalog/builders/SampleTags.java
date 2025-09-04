package be.pxl.services.productcatalog.builders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SampleTags {
    // TV tags
    public static List<String> getTvTags() {
        return new ArrayList<>(Arrays.asList(
            "LED", "OLED", "QLED", "4K", "8K",
            "Smart TV", "HDR", "Dolby Vision", "HDMI", "Bluetooth"
        ));}

    // Cell Phone tags
    public static List<String> getPhoneTags() {
        return new ArrayList<>(Arrays.asList(
            "Android", "iOS", "5G", "Dual SIM",
            "Wireless Charging", "Fast Charging",
            "Face ID", "Fingerprint Sensor"
        ));}

    // Computer tags
    public static List<String> getPcTags() {
        return new ArrayList<>(Arrays.asList(
            "Desktop", "All-in-One", "Gaming PC", "Workstation",
            "Intel", "AMD", "SSD", "HDD"
    ));}


    // Laptop tags
    public static List<String> getLaptopTags() {
        return new ArrayList<>(Arrays.asList(
            "Ultrabook", "Gaming Laptop", "2-in-1",
            "Touchscreen", "Lightweight", "MacBook", "Windows", "Linux"
        ));}
}
