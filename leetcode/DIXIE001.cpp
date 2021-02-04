#include <bits/stdc++.h> 
using namespace std;

// Comparator function to sort pairs 
// according to second value 
bool cmp(pair<string, int>& a, 
         pair<string, int>& b) 
{ 
    return a.second < b.second; 
} 
  
// Function to sort the map according 
// to value in a (key-value) pairs 
// void sort_(unordered_map<char, int>& M) 
// { 
  
//     // Declare vector of pairs 
//     vector<pair<char, int> > A; 
  
//     // Copy key-value pair from Map 
//     // to vector of pairs 
//     for (auto& it : M) { 
//         A.push_back(it); 
//     } 
  
//     // Sort using comparator function 
//     sort(A.begin(), A.end(), cmp); 
  
//     // Print the sorted value 
//     for (auto& it : A) { 
  
//         cout << it.first << ' '
//              << it.second << endl; 
//     } 
// } 

void printFrequency(string str,unordered_map<char,int>& M)
{


 
    // Traverse string str check if
    // current character is present
    // or not
    for (int i = 0; str[i]; i++) 
    {
        // If the current characters
        // is not found then insert
        // current characters with
        // frequency 1
        if (M.find(str[i]) == M.end()) 
        {
            M.insert(make_pair(str[i], 1));
        }
 
        // Else update the frequency
        else
        {
            M[str[i]]++;
        }
    }
 
    // Traverse the map to print the
    // frequency
    for (auto& it : M) {
        cout << it.first << ' ' << it.second << '\n';
    }
}

int main() {
    string _;
    getline(cin,_);
	char input[1000];
    for (size_t i = 0; i < 999; i++)
    {
        input[i] = getchar();
        if (input[i] == EOF)
        {
            int a = input[i];
            cout<<"eof: "<<a<<endl;
            break;
        }
        
    }
    
    // for (size_t i = 0; input[i]!=-1; i++)
    // {
    //     // cout<<input[i];

    // }

    unordered_map<char,int> M;
    printFrequency(input,M);

    cout<<"after sorted:\n";
    // sort_(M);
    
    
    
}