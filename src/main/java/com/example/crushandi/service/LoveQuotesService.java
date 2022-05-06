package com.example.crushandi.service;

import com.example.crushandi.dto.LoveQuotes;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LoveQuotesService {

    public LoveQuotes returnLoveQuotes() {
        LoveQuotes loveQuotes = new LoveQuotes();
        List<String> quotes = Arrays.asList("I want someone who will look at me the same way I look at chocolate cake.",

                "You want to know who I’m in love with? Read the first word again.",

                "I love you and I don’t want to lose you. Because my life has been better since the day I found out.",

                "I love you begins by I, but it ends up by you.",

                "I love you with every beat of my heart.",

                "I love you because you join me in my weirdness.",

                "I love you without knowing how, or when, or from where. I love you straightforwardly, without complexities or pride; so I love you because I know no other way.",

                "My love for you is past the mind, beyond my heart, and into my soul.",

                "And when I loved you, I realized, I have never truly loved anyone I realized, I never will truly love anyone the way I love you.",

                "When I tell you I love you, I don’t say it out of habit. I say it to remind you that you are the best thing that has ever happened to me.",

                "The day I met you, I found my missing piece. You complete me and make me a better person. I love you with all my heart and all my soul.",

                "You are the last thought in my mind before I drift off to sleep and the first thought when I wake up each morning.",

                "I love you like a fat kid loves cake.",

                "You’ve gotta dance like there’s nobody watching, Love like you’ll never be hurt, Sing like there’s nobody listening, And live like its heaven on earth.",

                "Darkness cannot drive out darkness: only light can do that. Hate cannot drive out hate: only love can do that.",

                "Age does not protect you from love, but love to some extent protects you from age.",

                "Love is never lost. If not reciprocated, it will flow back and soften and purify the heart.",

                "Life is the first gift, love is the second, and understanding the third.",

                "You never lose by loving. You always lose by holding back.",

                "Love is the expansion of two natures in such fashion that each includes the other, each is enriched by the other.",

                "Love does not consist of gazing at each other, but in looking together in the same direction.",

                "Kindness in words creates confidence. Kindness in thinking creates profundity. Kindness in giving creates love.",

                "When our community is in a state of peace, it can share that peace with neighboring communities, and so on. When we feel love and kindness towards others, it not only makes others feel loved and cared for, but it helps us also to develop inner happiness and peace.",

                "People think a soul mate is your perfect fit, and that’s what everyone wants. But a true soul mate is a mirror, the person who shows you everything that is holding you back, the person who brings you to your own attention so you can change your life.",

                "I’m here. I love you. I don’t care if you need to stay up crying all night long, I will stay with you. There’s nothing you can ever do to lose my love. I will protect you until you die, and after your death, I will still protect you. I am stronger than depression and I am braver than loneliness and nothing will ever exhaust me.",

                "Love is the emblem of eternity; it confounds all notion of time; effaces all memory of a beginning, all fear of an end.",

                "Love is an emotion experienced by the many and enjoyed by the few.",

                "Time and time again I have to pinch myself when I see you next to me. You are my dream come true.",

                "Thank you for stealing my eyes from the crowd and stealing my heart from me.",

                "The chances of meeting you on this planet are like finding a needle in haystack – a miracle happened.",

                "Life with you is tranquil yet full of surprises and I am in it for the long haul.",

                "Kissing you is my favorite hobby. Holding you is my favorite pastime.",

                "You are the single greatest source of my joy. You are the sun of my life and I revolve around you, you nourish me, you give me life.",

                "You are extraordinary, exquisite, impressive, magical and I am so in love with you.",

                "I look at you and see the rest of my life in front of my eyes.",

                "I hope you know that every time I tell you to get home safe, stay warm, have a good day, or sleep well, what I am really saying is that I love you. I love you so damn much that it is starting to steal other words’ meanings.",

                "You have bewitched me, body and soul, and I love… I love… I love you.",

                "Not so long ago I was alone and lost, and then you came along and I was home. Thank you for finding me.",

                "I love you not only for what you are but for what I am when I am with you.",

                "There isn’t one person in the world that I want more than I want you.",

                "Please don’t doubt my love for you; it’s the only thing I’m sure of.",

                "Sometimes I wonder if love is worth fighting for, but then I remember your face and I’m ready for war.",

                "There’s just something about you I’m scared to lose because I know I won’t find it in anyone else.",

                "I know I am in love with you because my reality is finally better than my dreams.",

                "Believing that you are mine forever is what makes me get up in the mornings.",

                "I love you the way a drowning man needs air. And it would destroy me to have you just a little.",

                "There are only two instances when I will ever want to be with you: now and forever."

        );


        loveQuotes.setLoveQuotes(quotes);
        return loveQuotes;
    }
}
