// C++ program for the above approach 

#include <bits/stdc++.h> 
using namespace std; 

// Comparator function to sort pairs 
// according to second value 
bool cmp(pair<char, int>& a, 
		pair<char, int>& b) 
{ 
	return a.second < b.second; 
} 

// Function to sort the map according 
// to value in a (key-value) pairs 
void sort_(map<char, int>& M) 
{ 

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

		cout << it.first << ' '
			<< it.second << endl; 
	} 
} 

// Driver Code 
int main() 
{ 

	// Declare Map 
	map<char, int> M; 

	// Given Map 
	M = { { 'G', 3 }, 
		{ 'T', 2 }, 
		{ 'W', 1 } }; 

	// Function Call 
	sort_(M); 
	return 0; 
} 
