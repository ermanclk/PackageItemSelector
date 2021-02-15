package com;

import com.domain.Item;
import com.domain.PackageConfig;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.util.LineParser.parseFileContent;

public class ApplicationRunner {

    public static void main(String[] args) {

        if (args.length == 0) {
            throw new IllegalArgumentException("Please provide input file path as argument.");
        }

        List<PackageConfig> configs = parseFileContent(args[0]);

        for (PackageConfig config : configs) {
            Set<Item> selectedItems = PackageItemSelector.fillPackage(config);

            if (selectedItems.isEmpty()) {
                System.out.println("-");
            } else {
                System.out.println(selectedItems.stream().map(Item::getId).collect(Collectors.joining(",")));
            }
        }
    }

}
