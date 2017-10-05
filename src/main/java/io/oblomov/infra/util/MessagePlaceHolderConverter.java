package io.oblomov.infra.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by TTBADALI on 27.01.2015.
 */
public class MessagePlaceHolderConverter
{
    public static String replaceVariablesInMessageWithValues(String message, Map<String, String> variableNameAndValues)
    {
        if(StringUtils.isNotBlank(message))
        {
            Set<String> variablesNamesInMessage = extractVariableNames(message);
            validateVariableNameAndValues(variableNameAndValues, variablesNamesInMessage);
            StrSubstitutor strSubstitutor = new StrSubstitutor(variableNameAndValues, "${","}");
            return strSubstitutor.replace(message);
        }
        return message;
    }


    private static Set<String> extractVariableNames(String message)
    {
        Set<String> variablesNamesInMessage = Sets.newHashSet();
        Pattern pattern = Pattern.compile("\\$\\{(.*?)}");
        Matcher matcher = pattern.matcher(message);
        while (matcher.find())
        {
            variablesNamesInMessage.add(matcher.group(1));
        }
        return variablesNamesInMessage;
    }

    private static void validateVariableNameAndValues(Map<String, String> variableNameAndValues, Set<String> variablesNamesInTemplateBody)
    {
        checkNotNull(variablesNamesInTemplateBody, "variable set can not be null");
        for (String templateVariableName : variablesNamesInTemplateBody)
        {
            if (!variableNameAndValues.keySet().contains(templateVariableName))
            {
                throw new IllegalArgumentException("Variable \"" + templateVariableName + "\" is found in the template but not in the key-value map!");
            }
        }
    }
}
