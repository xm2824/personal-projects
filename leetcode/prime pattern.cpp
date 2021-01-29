
#pragma GCC target("avx2")
#pragma GCC optimize ("O3")
#pragma GCC optimize ("unroll-loops")
#include<bits/stdc++.h>

#define f			first
#define sz(a)       ((int)(a).size())
#define s			second
#define all(v)		v.begin(),v.end()
#define pii 		pair<int,int>
#define vpii 		vector<pii>
#define eb			emplace_back
#define pb			push_back
#define fo(i,n)		for(int i=0;i<n;i++)
#define Fo(i,k,n)	for(int i=k;i<n;i++)
#define vi			vector<int>

using namespace std;
using ll  = long long;
using lld = long double;

const int oo = 0x3f3f3f3f;
const ll MOD = 1000000007;

//store strings in trie or map or hashmap ? ideally trie will be good
unordered_set<string> hs;
signed main(){

#ifndef ONLINE_JUDGE
	//freopen("in.txt" , "r" , stdin);
#endif
	ios_base::sync_with_stdio(0);cin.tie(0);

	int n;
	cin >> n;
	hs.reserve(n);
	hs.max_load_factor(.255);
	for(int i = 0 ; i < n ; i++){
		string str;
		cin >> str;
		hs.insert(str);
	}

	int q;
	cin >> q;
	vector<string> qry(q);
	for(int i = 0 ; i < q ; i++) cin >> qry[i];

	
	for(const auto & str : qry){
		if(hs.count(str)){
			cout << str << '\n';
			continue;
		}
		for(int i = 0 ; i < sz(str) ; i++){
			string a = str.substr(0 , i + 1);
			string b = str.substr(i + 1 , sz(str) - i - 1);
			if(hs.count(a) && hs.count(b)){
				cout << a << " " << b << "\n";
				break;
			}
		}
	
	}
    return 0;
}




