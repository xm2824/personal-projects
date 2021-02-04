#include<vector> 
#include<unordered_map>
#include<string>
#include<iostream>
#include<algorithm>
using namespace std;
std::vector<char> key;



// Comparator function to sort pairs 
// according to second value 
bool cmp(pair<char, int>& a, 
		pair<char, int>& b) 
{ 
    if(a.second<b.second) return true;
    else if (a.second==b.second)
    {
        return a.first<b.first;
    }
    else return false;
    
} 

// Function to sort the map according 
// to value in a (key-value) pairs 
void sort_(unordered_map<char, int>& M) 
{ 
    // cout<<"sorted:"<<endl;

	// Declare vector of pairs 
	vector<pair<char, int> > A; 

	// Copy key-value pair from Map 
	// to vector of pairs 
	for (auto& it : M) { 
		A.push_back(it); 
	} 

	// Sort using comparator function 
	sort(A.begin(), A.end(), cmp); 

	// Print the sorted value 
	for (auto& it : A) { 
            key.push_back(it.first);
	} 
} 

void printFrequency(string str,unordered_map<char,int>& M)
{


 
    // Traverse string str check if
    // current character is present
    // or not
    for (int i = 0; str[i]; i++) 
    {
        if (str[i]==EOF)
        {
            continue;
        }
        
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
    // for (auto& it : M) {
    //     cout << it.first << ' ' << it.second << '\n';
    // }
}

int main() {
    string _;
    getline(cin,_);
	char input[300000];
    for (size_t i = 0; i < 299999; i++)
    {
        char tmp = getchar();
        if(tmp != EOF){
            input[i] = tmp;
        }
        else{
            input[i]=-1;
            break;
        }
       
        
    }
    
    // for (size_t i = 0; input[i]!=-1; i++)
    // {
    //     // cout<<input[i];

    // }

    unordered_map<char,int> M;
    printFrequency(input,M);

    // cout<<"after sorted:\n";
    sort_(M);
    int size = M.size();

    
    

    
    unordered_map<char,char> mapping;
    for (size_t i = 0; i < size; i++)
    {
        mapping[key[i]]=key[size-1-i];
    }

    // for (auto &&i : mapping)
    // {
    //     cout<<i.first<<": "<<i.second<<endl;
    // }
    

    for (size_t i = 0; ; i++)
    {
        if (input[i] != -1)
        {
            input[i] = mapping.at(input[i]);
        }
        else {
            input[i]=0;
            break;
            }

        
        
    }
    cout<<input;
    
    
    
    
    
    
    
}