class Solution
{
    public:
    vector<string> longestWords(vector<string>& dict)
    {
        map<size_t,vector<string> > m;
        size_t maxLength = 0;
        for(auto it = dict.begin(); it != dict.end(); ++it)
        {
            maxLength = std::max(it->length(), maxLength);
            m[it->length()].push_back(*it);
        }

        return m[maxLength];
    }
};

class Solution {
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        HashMap<Integer, ArrayList<String>> map = new HashMap<Integer, ArrayList<String>>();

        int max = 0;
        for(int i = 0; i < dictionary.length; i++)
        {
            if(map.containsKey(dictionary[i].length()))
            {
                map.get(dictionary[i].length()).add(dictionary[i]);
            }
            else
            {
                ArrayList<String> arr = new ArrayList<String>();
                arr.add(dictionary[i]);
                map.put(dictionary[i].length(), arr);
            }
            max = Math.max(dictionary[i].length(), max);
        }
        return map.get(max);
    }
};

class Solution {
    /**
     * @param dictionary: an array of strings
     * @return: an arraylist of strings
     */
    ArrayList<String> longestWords(String[] dictionary) {
        // write your code here
        ArrayList<String> result = new ArrayList<String>();
        int max = 0;
        for(int i = 0; i < dictionary.length; i++)
        {
            max = Math.max(dictionary[i].length(), max);
        }

        for(String a : dictionary)
        {
            if(a.length() == max)
            {
                result.add(a);
            }
        }
        return result;
    }
};