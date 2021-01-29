#include <iostream>
using namespace std;

int main() {
	// your code goes here
    int t;
    cin >>t;
    getchar();
    for (size_t i = 0; i < t; i++)
    {
        int n;
        cin >>n;
        getchar();

        long sum = (1+n)*n /2;
        if(sum %2 == 0){
            cout<<"Case "<<i+1<<": "<<0<<endl;
        }
        else{
            cout<<"Case "<<i+1<<": "<<1<<endl;
        }
    }
    
	return 0;
}