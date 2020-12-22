#include <string>
#include <iostream>
using namespace std;
class Solution {
public:
    int firstUniqChar(string s1234556) {
         char ret= rec(s1234556);
         if(ret !='.') return s1234556.find(ret);
         return  -1;

    }

    char rec(string s1){
        cout<<s1<<endl;
        if (s1 == "" ){
            return '.';
        } else if (s1.size()==1) return s1[0];

        char peek = s1[0];
        string rest =  s1.substr(1);

        if (rest.find(peek)==string::npos){
            return peek;
        }
        else   {
            replace(rest.begin(),rest.end(),peek,(char)0);
            return rec(rest);
        }
    }
};

int main (){
    Solution a;
    a.firstUniqChar("dddccdbba");
}