#include <iostream>
#include <string>
#include <vector>
using namespace std;

int factmod(int n, int p) {
    vector<int> f(p);
    f[0] = 1;
    for (int i = 1; i < p; i++)
        f[i] = f[i-1] * i % p;

    int res = 1;
    while (n > 1) {
        if ((n/p) % 2)
            res = p - res;
        res = res * f[n%p] % p;
        n /= p;
    }
    return res; 
}

int main() {
	int t ;
    cin >> t;
    getchar();
    
    string lines[t];
    for (size_t i = 0; i < t; i++)
    {
        int n,p;
        cin >> n;
        cin >> p;
        getchar();
        long res = factmod(n,p);
        cout<<res<<endl;
    }


    
    
	return 0;
}