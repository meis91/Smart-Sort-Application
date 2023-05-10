package com.projectnetworld.model;

import java.math.BigInteger;
import java.text.CollationKey;
import java.text.Collator;
import java.util.*;

public class SmartStringCollator implements Comparator<String> {
    private final Collator collator;
    private final int SPACE_KEY = 0;
    private final int SEPARATOR_KEY = 1;
    private final int DIGIT_KEY = 2;
    private final int LETTER_KEY = 3;
    private final String SPACE = ".*\\s+.*";
    private final String DIGIT = "\\d+";
    private final String LETTER = "\\w+|[a-zA-Z]";
    private final int A = -1;
    private final int B = 1;
    private final int EQUAL = 0;

    public SmartStringCollator(Locale locale) {
        this.collator = Collator.getInstance(locale);
        this.collator.setStrength(Collator.SECONDARY);
    }

    private List<String> getGroups(String line) {
        List<String> groups = new ArrayList<>();
        StringBuilder standardGroup = new StringBuilder();
        StringBuilder spaceGroup = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (Character.isLetter(character)) {
                handleNewGroup(groups, spaceGroup);
                standardGroup.append(character);
            } else if (Character.isDigit(character)) {
                handleNewGroup(groups, spaceGroup);
                standardGroup.append(character);
            } else if (Character.isSpaceChar(character)) {
                handleNewGroup(groups, standardGroup);
                spaceGroup.append(character);
            } else {
                handleNewGroup(groups, standardGroup);
                groups.add(Character.toString(character));
            }
        }
        if (standardGroup.length() > 0) {
            groups.add(standardGroup.toString());
        }
        if (spaceGroup.length() > 0) {
            groups.add(spaceGroup.toString());
        }
        return groups;
    }

    private void handleNewGroup(List<String> groups, StringBuilder spaceGroup) {
        if (spaceGroup.length() > 0) {
            groups.add(spaceGroup.toString());
            spaceGroup.setLength(0);
        }
    }

    private int getGroupType(String group) {
        if (group.matches(SPACE)) {
            return SPACE_KEY;
        } else if (group.matches(DIGIT)) {
            return DIGIT_KEY;
        } else if (group.matches(LETTER)) {
            return LETTER_KEY;
        } else {
            return SEPARATOR_KEY;
        }
    }

    @Override
    public int compare(String a, String b) {
        List<String> groupsA = getGroups(a);
        List<String> groupsB = getGroups(b);
        int i = 0;
        while (i < groupsA.size() && i < groupsB.size()) {
            String groupA = groupsA.get(i);
            String groupB = groupsB.get(i);
            int typeA = getGroupType(groupA);
            int typeB = getGroupType(groupB);
            if (typeA != typeB) {
                if (typeA == SPACE_KEY) {
                    return -1; // spaces come first
                } else if ( typeB == SPACE_KEY) {
                    return 1; // spaces come first
                } else if (typeA == SEPARATOR_KEY && (typeB == LETTER_KEY || typeB == DIGIT_KEY)) {
                    return -1; // separators come next
                } else if ((typeA == LETTER_KEY || typeA == DIGIT_KEY) && typeB == SEPARATOR_KEY) {
                    return 1; // separators come next
                } else if (typeA == DIGIT_KEY && typeB == LETTER_KEY) {
                    return -1; // digits come before letters
                } else if (typeA == LETTER_KEY && typeB == DIGIT_KEY) {
                    return 1; // letters come after digits
                }
            } else {
                if (typeA == SEPARATOR_KEY) {
                    return 0; // both groups are spaces, consider them equal
                } else if (typeA == DIGIT_KEY) {
                    // compare groups of digits by numeric value
                    BigInteger valueA = new BigInteger(groupA);
                    System.out.println("valueA = " + valueA);
                    BigInteger valueB = new BigInteger(groupB);
                    System.out.println("valueB = " + valueB);
                    int comparisonResult = valueA.compareTo(valueB);
                    System.out.println("comparisonResult = " + comparisonResult);
                    if (comparisonResult != 0) {
                        return comparisonResult;
                    }
                } else {
                    // compare groups of same type using standard collatorB
                    CollationKey keyA = collator.getCollationKey(groupA);
                    CollationKey keyB = collator.getCollationKey(groupB);
                    int comparisonResult = keyA.compareTo(keyB);
                    if (comparisonResult != 0) {
                        return comparisonResult;
                    }
                }
            }
            i++;
        }
        // compare remaining groups by length
        return groupsA.size() - groupsB.size();
    }
}
