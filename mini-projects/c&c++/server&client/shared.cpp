#include "common.hpp"
#include "shared.hpp"
using namespace std;

string Shared::getValue() const {
    return this->value;
}

void Shared::setValue(string &val) {
this->value = move(val);
}

Shared::Shared() {
this->rwlock = PTHREAD_RWLOCK_INITIALIZER;
}
