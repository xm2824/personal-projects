#include <vector>
#include <iostream>
#include <set>
using namespace std;
int main(){
    vector<set<int> > res(20);

    res[0].insert(1);

	for(auto&& i: res[0])
	{
		std::cout<<i<<"\n";
	}
}