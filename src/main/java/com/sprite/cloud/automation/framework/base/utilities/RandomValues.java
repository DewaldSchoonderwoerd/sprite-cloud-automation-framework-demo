package com.sprite.cloud.automation.framework.base.utilities;


import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;

import static org.passay.CharacterCharacteristicsRule.ERROR_CODE;

public class RandomValues {

    public static int randomIntInRange(int max, int min) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static String randomPassword() {
        PasswordGenerator passwordGenerator = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        CharacterData nonAlphanumericChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "~`!@#$.?<{[]}()=+_->";
            }
        };
        CharacterRule nonAlphaCharRule = new CharacterRule(nonAlphanumericChars);
        splCharRule.setNumberOfCharacters(2);

        return passwordGenerator.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule, nonAlphaCharRule);
    }
}
