package com.example.test;

import java.io.FileInputStream;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

public class LogCensorUtil
{
    private static final char DOUBLE_QUOTE_CHAR = '\"';
    private static final char OPEN_CURLY_BRACES_CHAR = '{';
    private static final char CLOSE_CURLY_BRACES_CHAR = '}';
    private static final char COMMA_CHAR = ',';
    private static final char EMPTY_SPACE_CHAR = ' ';
    private static final char OPEN_BRACKET_CHAR = '[';
    private static final char CLOSE_BRACKET_CHAR = ']';
    private static final char TRUE_CHAR = 't';
    private static final char FALSE_CHAR = 'f';
    private static final char NULL_CHAR = 'n';
    private static final char CR_CHAR = '\r';
    private static final char LF_CHAR = '\n';
    private static final char BACKWARD_SLASH = '\\';
    private static final String NUMBERS = "-0123456789";

    private static final String REPLACE_STRING = "(value)";

    public static String censorSensitiveInformation(String msg, String key, String type)
    {
        if ("JSON".equals(type))
        {
            return censorSensitiveJSONInformation(msg, key);
        }
        else if ("XML".equals(type))
        {
            return censorSensitiveXMLInformation(msg, key);
        }

        return msg;
    }

    private static String censorSensitiveXMLInformation(String msg, String key)
    {
        if (Objects.nonNull(msg))
        {
            while (true)
            {
                int startPos = msg.indexOf("<" + key + ">");
                int endPos = msg.indexOf("</" + key + ">");

                if (startPos >= 0 && endPos >= 0)
                {
                    String p = msg.substring(0, startPos);

                    p = p + "<" + key + "_censor>(value)</" + key + "_censor>";

                    msg = p + msg.substring(endPos + key.length() + 3);

                    continue; // to support multiple same key
                }
                else if (msg.indexOf("<" + key + "/>") >= 0)
                {
                    // this is to censor if got field without value
                    // example : <pin/> or <MPassword/>
                    msg = msg.replaceAll("<" + key + "/>", "<" + key + "_censor>(empty)</" + key + "_censor>");
                }
                break;
            }
        }

        return msg;
    }

    private static String censorSensitiveJSONInformation(String msg, String key)
    {

        if (Objects.nonNull(msg))
        {
            int currentPosition = 0;

            while (true)
            {
                // find the first occurence of key
                int position = msg.indexOf(DOUBLE_QUOTE_CHAR + key + DOUBLE_QUOTE_CHAR, currentPosition);

                if (position >= 0)
                {
                    position = position + (DOUBLE_QUOTE_CHAR + key + DOUBLE_QUOTE_CHAR).length();

                    currentPosition = position;

                    for (int i = position, i_total = msg.length(); i < i_total; i++)
                    {
                        int startIndex = i;
                        if (msg.charAt(i) == EMPTY_SPACE_CHAR || msg.charAt(i) == ':' || msg.charAt(i) == CR_CHAR || msg.charAt(i) == LF_CHAR)
                        {
                            // to cater multiple value
                            continue;
                        }
                        else if (msg.charAt(i) == OPEN_BRACKET_CHAR)// start of array eg [1,2,3] or ["a","b","c"]
                        {
                            i = findJSONArrayIndex(msg, i);
                            msg = replaceJsonValue(msg, startIndex, i);
                            break;

                        }
                        else if (NUMBERS.indexOf(msg.charAt(i)) != -1 || msg.charAt(i) == NULL_CHAR || msg.charAt(i) == TRUE_CHAR || msg.charAt(i) == FALSE_CHAR)// number
                                                                                                                                                                 // start
                        {
                            i = findJSONValueIndex(msg, i);
                            msg = replaceJsonValue(msg, startIndex, i);
                            break;
                        }
                        else if (msg.charAt(i) == DOUBLE_QUOTE_CHAR) // string start
                        {
                            i = findJSONStringIndex(msg, i);
                            msg = replaceJsonValue(msg, startIndex, i);
                            break;

                        }
                        else if (msg.charAt(i) == OPEN_CURLY_BRACES_CHAR) // start of object eg {"Name" : "Jack"}
                        {
                            i = findJSONObjectIndex(msg, i);
                            msg = replaceJsonValue(msg, startIndex, i);
                            break;
                        }
                    }
                }
                else
                {
                    break;
                }

            }
        }

        return msg;
    }

    /**
     * 
     * @param msg
     * @param index
     * @return last index of the value.
     */
    private static int findJSONStringIndex(String msg, int index)
    {
        for (int i = ++index, i_total = msg.length(); i < i_total; i++)
        {
            // if current char is double quete with no escape char in front,
            // string end
            if (msg.charAt(i) == DOUBLE_QUOTE_CHAR && msg.charAt(i - 1) != BACKWARD_SLASH)
            {
                // return ++i to remove the double quote at the end of string
                return ++i;
            }

        }

        // if this index is return, nothing is change
        return index;

    }

    /**
     * 
     * @param msg
     * @param index
     * @return last index of the value. Expected value eg
     *         -100,1234,10.23,true,false,null.
     */
    private static int findJSONValueIndex(String msg, int index)
    {
        index++;

        // if there are no special char to end the value, increase the index
        while (msg.charAt(index) != EMPTY_SPACE_CHAR && msg.charAt(index) != COMMA_CHAR && msg.charAt(index) != CLOSE_CURLY_BRACES_CHAR && msg.charAt(index) != CLOSE_BRACKET_CHAR) // find
                                                                                                                                                                                    // end
                                                                                                                                                                                    // of
                                                                                                                                                                                    // value
        {
            index++;
        }

        return index;

    }

    /**
     * 
     * @param msg
     * @param index
     * @return last index of the value. Expected value eg {"Name" : "Jack"}.
     */
    private static int findJSONObjectIndex(String msg, int index)
    {
        // current index is { , increase the index for next char
        for (int i = ++index, i_total = msg.length(); i < i_total; i++)
        {
            if (msg.charAt(i) == DOUBLE_QUOTE_CHAR) // string start
            {
                i = findJSONStringIndex(msg, i);
            }
            else if (NUMBERS.indexOf(msg.charAt(i)) != -1 || msg.charAt(i) == NULL_CHAR || msg.charAt(i) == TRUE_CHAR || msg.charAt(i) == FALSE_CHAR) // number
                                                                                                                                                      // start
            {
                i = findJSONValueIndex(msg, i);

            }
            else if (msg.charAt(i) == OPEN_CURLY_BRACES_CHAR)
            {
                i = findJSONObjectIndex(msg, i);
            }
            else if (msg.charAt(i) == OPEN_BRACKET_CHAR)
            {
                i = findJSONArrayIndex(msg, i);
            }

            if (msg.charAt(i) == CLOSE_CURLY_BRACES_CHAR) // object end
            {
                // return ++i to remove the close curly braces at the end of
                // object
                return ++i;
            }

        }

        return index;

    }

    /**
     * 
     * @param msg
     * @param index
     * @return last index of the value. Expected value eg [1,2,3] or
     *         ["a","b","c"].
     */
    private static int findJSONArrayIndex(String msg, int index)
    {
        // current index is [ , increase the index for next char
        int endList = index + 1;

        // if array is empty, return original index
        if (msg.charAt(index + 1) == CLOSE_BRACKET_CHAR)
        {
            return index;
        }
        do
        {

            if (msg.charAt(endList) == DOUBLE_QUOTE_CHAR)
            {
                endList = findJSONStringIndex(msg, endList);
            }
            else if (msg.charAt(endList) == OPEN_CURLY_BRACES_CHAR)
            {
                endList = findJSONObjectIndex(msg, endList);
            }
            else if (NUMBERS.indexOf(msg.charAt(endList)) != -1 || msg.charAt(endList) == NULL_CHAR || msg.charAt(endList) == FALSE_CHAR || msg.charAt(endList) == TRUE_CHAR) // null
                                                                                                                                                                              // start
            {
                endList = findJSONValueIndex(msg, endList);

            }
            else if (msg.charAt(endList) == OPEN_BRACKET_CHAR)
            {
                endList = findJSONArrayIndex(msg, endList);
            }
            else
            {
                // increase the endList if encounter white space
                endList++;
            }

        }
        // loop until close bracket is encountered
        while (msg.charAt(endList) != CLOSE_BRACKET_CHAR);

        // return ++i to remove the close bracket at the end of object
        return ++endList;

    }

    public static String replaceJsonValue(String msg, int startIndex, int endIndex)
    {
        // replace wording here
        String p = msg.substring(0, startIndex);
        p = p + REPLACE_STRING;

        // remove old value here
        msg = p + msg.substring(endIndex);

        return msg;
    }

    public static void main(String[] s) throws Exception
    {
        String msg = readFile();
        String key = "password";

        String output = censorSensitiveJSONInformation(msg, key);
        System.out.print(output);
    }

    public static String readFile() throws Exception
    {
        FileInputStream inputStream = new FileInputStream("D:/abc.txt");
        String everything = IOUtils.toString(inputStream);
        inputStream.close();
        return everything;
    }
}
