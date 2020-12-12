#include<iostream>

namespace str{
  class String
  {
      unsigned _length{0};
    const char* begin;
    char* _begin= nullptr;
    String(const char* string, unsigned length):begin(string),_length(length){};
    public:
      String(const char* string){
      begin=string;
      for (const char* i = begin; *i !='\0'; i++)
      {
        _length++;
      }
    }
    String(const String& other){
        this->_length=other._length;
        _begin=new char[_length + 1];
        for (int i = 0; i < _length; ++i) {
            _begin[i]=other.begin[i];
        }
        _begin[_length]='\0';
        begin=_begin;

    }
    String(String& other){
        this->_length=other._length;
         _begin=new char[_length + 1];
        for (int i = 0; i < _length; ++i) {
            _begin[i]=other.begin[i];
        }
        _begin[_length]='\0';
        begin=_begin;

      }

      [[nodiscard]] unsigned length() const {
          return _length;
      }

      String(String&& other) noexcept {
          this->_length=other._length;
          _begin=new char[_length + 1];
          for (int i = 0; i < _length; ++i) {
              _begin[i]=other.begin[i];
          }
          _begin[_length]='\0';
          begin=_begin;


    }

    String& operator=(const String& other){
        this->_length=other._length;
        char tmp[_length + 1];
        for (int i = 0; i < _length; ++i) {
            tmp[i]=other.begin[i];
        }
        tmp[_length]='\0';
        begin=tmp;
        return *this;
    }
    String& operator=(String&& other)noexcept {
        begin=other.begin;
        _length=other._length;
        other.begin=nullptr;
        other._length=0;
        return *this;
    }
    String&operator=(const char* other) noexcept {
        begin=other;
        for (const char* i = begin; *i !='\0'; i++)
        {
            _length++;
        }
        return *this;
    }

    String(const String&& other) = delete;

      friend std::ostream& operator<<(std::ostream& out,const String& data){
            out << data.begin;
          return out;
      }

      String operator+(const char* str){
          if (this== nullptr || begin ==nullptr || _length == 0){
              begin=str;
              for (const char* i = begin; *i !='\0'; i++)
              {
                  _length++;
              }
              return *this;
          }
          else{unsigned length=0;
              for (const char* tmp = str;*tmp!='\0'   ; tmp++) {
                  length++;
              }
              char ret[_length+length+1];

              for (unsigned i = 0; i < _length; ++i) {
                  ret[i]=begin[i];
              }

              for (unsigned j = _length; j < _length+length; ++j) {
                  ret[j]=str[j-_length];

              }
              ret[_length+length]='\0';

              String Ret(ret,_length+length);
              return Ret;
          }

      }
      ~String(){
        if(_begin) delete[] (_begin);
      }





};
}
int main(){
    using namespace str;
    using namespace std;
    String a="hello";
    cout<<a.length()<<'\n';
    cout<<a<<'\n';
    String b =a+" world";
    cout<<b<<'\n';

    const String c = "const";
    String d =c;
    cout<<d<<'\n';
}