package hr.java.production.utility;

import hr.java.production.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for finding objects with specific properties.
 * <p>
 * This class provides static methods for finding objects such as factories, stores, and items with specific properties.
 * It uses a {@code Logger} object to log errors and information.
 */
public class InventoryAnalyzer {
    private static final Logger logger = LoggerFactory.getLogger(InventoryAnalyzer.class);


    public static <T extends NamedEntity> String getNamesAsString(Collection<T> entityCollection){
        return entityCollection.stream()
                .map(NamedEntity::getName)
                .collect(Collectors.joining(", "));
    }


}