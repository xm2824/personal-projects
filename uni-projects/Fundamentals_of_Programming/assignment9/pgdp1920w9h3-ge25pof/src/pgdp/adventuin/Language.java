package pgdp.adventuin;

public enum Language {
    
    ENGLISH {
            public String getLocalizedChristmasGreeting(String greeterName){
                return greeterName + " wishes you a Merry Christmas!";
        }
    },
    GERMAN{
        public String getLocalizedChristmasGreeting(String greeterName){
                return "Fröhliche Weihnachten wünscht dir "+greeterName+"!";
        }
    },
    CHINESE{
        public String getLocalizedChristmasGreeting(String greeterName){
                return greeterName + ": 聖誕節快樂!";
        }
    };

    public abstract String getLocalizedChristmasGreeting(String greeterName);
}
