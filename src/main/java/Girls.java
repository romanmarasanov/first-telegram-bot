import java.util.Random;

public class Girls {
    public final Random randomizer = new Random();
    public String[] girlsArray = {
            "https://fsa.zobj.net/crop.php?r=cleNJvGcLQqTI5G8o2vze9nCdXDm8QEPj_qIf3XxwK893wh54qUF2m_Xa5UTl23BL-BAmi0K7Qw59m0TwYAwajzhkT60_IWqr-zdyyJwF6ZRj1-74mFeJ_UpqXr_6iEHN7vCnE_e9DhF3eQq",
            "https://i.pinimg.com/originals/35/26/fc/3526fca5af9eaa620475a131a62cf1e6.jpg",
            "https://ih0.redbubble.net/image.675681423.7320/flat,1000x1000,075,f.u2.jpg",
            "https://i.pinimg.com/originals/b7/25/12/b725125aaebafbcbf2fb3886a55d2d6f.jpg",
            "https://i.pinimg.com/originals/0e/45/a8/0e45a874482af5f7d523282e12bf8a75.jpg",
            "https://ih1.redbubble.net/image.1045074544.0774/flat,750x1000,075,f.jpg",
            "https://image.winudf.com/v2/image/Y29tLnN1YWRhaDA2MTEuYW5pbWVnaXJsa2F3YWlpYXBwX3NjcmVlbl8yXzE1MjM4OTA0OTRfMDI0/screen-2.jpg?fakeurl=1&type=.jpg",
            "https://fiverr-res.cloudinary.com/images/q_auto,f_auto/gigs/123459056/original/b44f8e1e31b148f6b74f2e6db0370cb473b75be5/draw-cute-anime-girls.png",
            "https://www.pngkey.com/png/detail/180-1804708_beautiful-anime-girl-manga-anime-anime-art-zero.png",
            "https://www.kindpng.com/picc/m/325-3250608_anime-girl-brown-hair-green-eyes-hd-png.png",
            "https://wallpapercave.com/wp/wp5114631.jpg",
            "https://img5.goodfon.ru/wallpaper/big/5/b7/demon-girl-horns-red-eyes-pointed-ears-candles-staff-fantasy.jpg",
            "https://www.vippng.com/png/detail/277-2775027_for-make-america-great-again-hat-anime-girl.png",
            "https://c4.wallpaperflare.com/wallpaper/186/251/662/anime-anime-girls-digital-art-artwork-portrait-display-hd-wallpaper-preview.jpg",
            "https://www.pngkey.com/png/detail/969-9698754_drawing-shark-anime-report-abuse-tumblr-art-girl.png",
            "https://www.pngitem.com/pimgs/m/17-178294_anime-girls-anime-girl-cute-anime-art-girl.png",
            "https://i.imgur.com/HsRl7Gw.jpg",
            "https://pbs.twimg.com/media/D7_w-jKWwAE_nr9.jpg",
            "https://c4.wallpaperflare.com/wallpaper/373/803/757/anime-anime-girls-digital-art-artwork-2d-hd-wallpaper-preview.jpg",
            "https://pbs.twimg.com/media/D7nDFPUW0AAtzWE.jpg",
            "https://i.imgur.com/mjExSai.jpg",
            "https://www.teahub.io/photos/full/98-987456_photo-wallpaper-look-girl-hand-anime-art-squints.jpg",
            "https://cs11.pikabu.ru/post_img/big/2018/11/29/8/1543496476147682511.jpg",
            "https://cs10.pikabu.ru/post_img/2018/12/07/9/1544194210198046811.jpg",
            "https://i.pinimg.com/originals/62/b0/30/62b030e0d1f53810443038c0ff26b5c9.png",
    };

    public String getRandomGirlUrl() {
        int index = Math.abs(randomizer.nextInt()) % girlsArray.length;
        return girlsArray[index];
    }
}
