#include "common.hpp"
#include "shared.hpp"

#include <utility>
using namespace std;

string Shared::getValue() const {
    return this->value;
}

void Shared::setValue(string val) {
this->value = move(val);
}

Shared::Shared(string value) {
this->rwlock = PTHREAD_RWLOCK_INITIALIZER;
this->value = std::move(value);

}
