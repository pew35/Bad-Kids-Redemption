package edu.northeastern.demostructure;

public class Images {
    private boolean boy;
    private String b_lowSan = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190472006762606/b_lowSan.png?ex=662ec7d5&is=661c52d5&hm=b4826c281f7204b3f331271219a75800c10f31bd45c4436b7c1350a6a0a74154&";
    private String b_magic = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190473109999716/b0magic.png?ex=662ec7d6&is=661c52d6&hm=2ae880ad0de3b36d33b374eac8cae1d45c14680c45b3604f309f557b7e8a38be&";
    private String b_love = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190473852387388/b-love.png?ex=662ec7d6&is=661c52d6&hm=48f52dc6c6c07b9d1a691f59a85f672beb210daf717126366e96914b9ef621a4&";
    private String b_strong = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190474796109854/b-strong.png?ex=662ec7d6&is=661c52d6&hm=9ecd1e089fb11e0b284a49a625b08c6935f4186488d284861ce0f90198d11239&";

    private String b_study = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190475571920929/b-study.png?ex=662ec7d6&is=661c52d6&hm=a0000650b5a09af4b11bc74f4ba20e96cdca72f582646403de3519c56231c87f&";
    private String g_lowSan = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190476910035004/g-lowSan.png?ex=662ec7d6&is=661c52d6&hm=aaa7dfb8beaa79410318a325c6fa636ba3ac5120fee1deb0d8dd2ac5ae8a0cdd&";
    private String g_magic = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190477585186897/g-magic.png?ex=662ec7d7&is=661c52d7&hm=9328898d5caebfd770fc50f8746410de790028e15a4b597605e343607d74ea7b&";
    private String g_love = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190476268310548/g-love.png?ex=662ec7d6&is=661c52d6&hm=57b97a8073d3c90b11cd068a2cea156fcfb93bf8f5ff595b6e6e22ba0b9f104f&";
    private String g_strong = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190478172524584/g-strong.png?ex=662ec7d7&is=661c52d7&hm=0de45196bf8e248417418c35a221a389e4e3413c3f55a44866bd80129761e952&";
    private String g_study = "https://cdn.discordapp.com/attachments/1092716491532664895/1229190478822637598/g-study.png?ex=662ec7d7&is=661c52d7&hm=dcdf2114250adf6199d67355d27b4a7c9f6144f0aa9b59cb0d08432ca3510d3b&";
    private String study = "https://cdn.discordapp.com/attachments/1092716491532664895/1227468721727995954/00212-666687029.png?ex=66288454&is=66160f54&hm=43f1c763fb6a3f51b92b24fca4373a23e44a2da4ff10a738b5316b264f3ec207&";
    private String dating = "";
    private String exercise = "https://cdn.discordapp.com/attachments/1092716491532664895/1227470496115724328/00243-3158460996.png?ex=662885fb&is=661610fb&hm=00910310d406439c2506579f93f46ddd2a2cc5bd5087f0e5c0522736b32639d0&";
    private String magic = "https://cdn.discordapp.com/attachments/1092716491532664895/1227470850920419409/00247-1478110581.png?ex=66288650&is=66161150&hm=fe23a440b7197b6e7cb97f2506defb31cf64c808432cc78d40fc4e33063a0c20&";

    public Images(boolean boy){
        this.boy = boy;
    }

    public String getEvents(int n){
        switch (n){
            case 0: return exercise;
            case 1: return study;
            case 2: return dating;
            case 3: return magic;
            default: return null;
        }
    }

    public String getEnding(int num){
        if (this.boy){
            switch (num){
                case -1: return b_lowSan;
                case 1: return b_strong;
                case 2: return b_study;
                case 3: return b_love;
                case 4: return b_magic;
                default: return null;
            }
        }else {
            switch (num){
                case -1: return g_lowSan;
                case 1: return g_strong;
                case 2: return g_study;
                case 3: return g_love;
                case 4: return g_magic;
                default: return null;
            }
        }
    }
}
