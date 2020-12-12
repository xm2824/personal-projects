#ifndef HELPER_H
#define HELPER_H

inline void toLower(const char* src, unsigned strlen,char* dest){
for (unsigned i = 0; i < strlen; i++)
{
    
    if(src[i]>='A'&& src[i]<= 'Z')
    {
        dest[i] = src[i]+32;
    }
    else dest[i] = src[i];
}
} 


#endif
