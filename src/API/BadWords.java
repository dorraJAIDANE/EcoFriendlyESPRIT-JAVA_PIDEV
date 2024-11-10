/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

//import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
//import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
//import net.openhft.hashing.LongHashFunction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Grati Eya
 */
public class BadWords {
    
    private static List<String> badWords = new ArrayList<>();

    static {
        badWords.add("Shit");
        badWords.add("Bollocks");
        badWords.add("Bugger");
        badWords.add("Asshole");
        badWords.add("Fuck");
        badWords.add("Son of a bitch");
        badWords.add("DicK");
        badWords.add("Tits");
        badWords.add("Munter");
        badWords.add("Cheese and crackers");
        badWords.add("Motherfucker");
        badWords.add("Bastard");
        badWords.add("Cunt");
        badWords.add("Damn");
        badWords.add("Bitch");
        badWords.add("Bloody");
        badWords.add("Arse");
        badWords.add("Jesus");
        badWords.add("Bullshit");
        badWords.add("Pissed");
        badWords.add("Cow");
        badWords.add("Sod");
        badWords.add("ass");
        badWords.add("arsehead");
        badWords.add("arsehole");
        badWords.add("brotherfucker");
        badWords.add("bullshit");
        badWords.add("child-fucker");
        badWords.add("Christ on a bike");
        badWords.add("Christ on a cracker");
        badWords.add("cock");
        badWords.add("cocksucker");
        badWords.add("crap");
        badWords.add("cunt");
        badWords.add("damn it");
        badWords.add("dickhead");
        badWords.add("dyke");
        badWords.add("fatherfucker");
        badWords.add("frigger");
        badWords.add("goddamn");
        badWords.add("godsdamn");
        badWords.add("hell");
        badWords.add("holy shit");
        badWords.add("horseshit");
        badWords.add("in shit");
        badWords.add("Jesus Christ");
        badWords.add("2g1c");
        badWords.add("2 girls 1 cup");
        badWords.add("acrotomophilia");
        badWords.add("alabama hot pocket");
        badWords.add("alaskan pipeline");
        badWords.add("anal");
        badWords.add("anilingus");
        badWords.add("anus");
        badWords.add("apeshit");
        badWords.add("arsehole");
        badWords.add("DISABLEDass");
        badWords.add("assmunch");
        badWords.add("auto erotic");
        badWords.add("autoerotic");
        badWords.add("babeland");
        badWords.add("baby batter");
        badWords.add("baby juice");
        badWords.add("ball gag");
        badWords.add("ball gravy");
        badWords.add("ball kicking");
        badWords.add("ball licking");
        badWords.add("ball sack");
        badWords.add("ball sucking");
        badWords.add("bangbros");
        badWords.add("bareback");
        badWords.add("barely legal");
        badWords.add("barenaked");
        badWords.add("bastard");
        badWords.add("bastardo");
        badWords.add("bastinado");
        badWords.add("beaner");
        badWords.add("beaners");
        badWords.add("beaver cleaver");
        badWords.add("beaver lips");
        badWords.add("bestiality");
        badWords.add("big black");
        badWords.add("big breasts");
        badWords.add("big knockers");
        badWords.add("big tits");
        badWords.add("bimbos");
        badWords.add("birdlock");
        badWords.add("bitches");
        badWords.add("black cock");
        badWords.add("blonde action");
        badWords.add("blonde on blonde action");
        badWords.add("blowjob");
        badWords.add("blow job");
        badWords.add("blow your load");
        badWords.add("blue waffle");
        badWords.add("blumpkin");
        badWords.add("bollocks");
        badWords.add("bondage");
        badWords.add("boner");
        badWords.add("boob");
        badWords.add("boobs");
        badWords.add("booty call");
        badWords.add("brown showers");
        badWords.add("brunette action");
        badWords.add("bukkake");
        badWords.add("bulldyke");
        badWords.add("bullet vibe");
        badWords.add("bullshit");
        badWords.add("bung hole");
        badWords.add("bunghole");
        badWords.add("busty");
        badWords.add("buttcheeks");
        badWords.add("butthole");
        badWords.add("camel toe");
        badWords.add("camgirl");
        badWords.add("camslut");
        badWords.add("camwhore");
        badWords.add("carpet muncher");
        badWords.add("carpetmuncher");
        badWords.add("chocolate rosebuds");
        badWords.add("circlejerk");
        badWords.add("cleveland steamer");
        badWords.add("clitoris");
        badWords.add("clover clamps");
        badWords.add("clusterfuck");
        badWords.add("cock");
        badWords.add("cocks");
        badWords.add("coprolagnia");
        badWords.add("coprophilia");
        badWords.add("cornhole");
        badWords.add("coon");
        badWords.add("coons");
        badWords.add("creampie");
        badWords.add("cumming");
        badWords.add("cunnilingus");
        badWords.add("cunt");
        badWords.add("darkie");
        badWords.add("date rape");
        badWords.add("daterape");
        badWords.add("deep throat");
        badWords.add("deepthroat");
        badWords.add("dendrophilia");
        badWords.add("dildo");
        badWords.add("dingleberry");
        badWords.add("dingleberries");
        badWords.add("dirty pillows");
        badWords.add("dirty sanchez");
        badWords.add("doggie style");
        badWords.add("doggiestyle");
        badWords.add("doggy style");
        badWords.add("doggystyle");
        badWords.add("dog style");
        badWords.add("dolcett");
        badWords.add("domination");
        badWords.add("dominatrix");
        badWords.add("dommes");
        badWords.add("donkey punch");
        badWords.add("double dong");
        badWords.add("double penetration");
        badWords.add("dp action");
        badWords.add("dry hump");
        badWords.add("eat my ass");
        badWords.add("ecchi");
        badWords.add("ejaculation");
        badWords.add("erotic");
        badWords.add("erotism");
        badWords.add("escort");
        badWords.add("eunuch");
        badWords.add("faggot");
        badWords.add("fecal");
        badWords.add("fellatio");
        badWords.add("feltch");
        badWords.add("female squirting");
        badWords.add("femdom");
        badWords.add("figging");
        badWords.add("fingerbang");
        badWords.add("fingering");
        badWords.add("fisting");
        badWords.add("foot fetish");
        badWords.add("footjob");
        badWords.add("frotting");
        badWords.add("fuck");
        badWords.add("fuck buttons");
        badWords.add("fuckin");
        badWords.add("fucking");
        badWords.add("fucktards");
        badWords.add("fudge packer");
        badWords.add("fudgepacker");
        badWords.add("futanari");
        badWords.add("gang bang");
        badWords.add("gay sex");
        badWords.add("genitals");
        badWords.add("giant cock");
        badWords.add("girl on top");
        badWords.add("girls gone wild");
        badWords.add("goatcx");
        badWords.add("goatse");
        badWords.add("god damn");
        badWords.add("gokkun");
        badWords.add("golden shower");
        badWords.add("goodpoop");
        badWords.add("goo girl");
        badWords.add("goregasm");
        badWords.add("grope");
        badWords.add("group sex");
        badWords.add("g-spot");
        badWords.add("hand job");
        badWords.add("handjob");
        badWords.add("hard core");
        badWords.add("hardcore");
        badWords.add("hentai");
        badWords.add("homoerotic");
        badWords.add("honkey");
        badWords.add("hooker");
        badWords.add("hot carl");
        badWords.add("hot chick");
        badWords.add("how to kill");
        badWords.add("how to murder");
        badWords.add("huge fat");
        badWords.add("humping");
        badWords.add("incest");
        badWords.add("intercourse");
        badWords.add("jack off");
        badWords.add("jail bait");
        badWords.add("jailbait");
        badWords.add("jelly donut");
        badWords.add("jerk off");
        badWords.add("jigaboo");
        badWords.add("jiggaboo");
        badWords.add("jiggerboo");
        badWords.add("jizz");
        badWords.add("juggs");
        badWords.add("kike");
        badWords.add("kinbaku");
        badWords.add("kinkster");
        badWords.add("kinky");
        badWords.add("knobbing");
        badWords.add("leather restraint");
        badWords.add("leather straight jacket");
        badWords.add("lemon party");
        badWords.add("lolita");
        badWords.add("lovemaking");
        badWords.add("make me come");
        badWords.add("male squirting");
        badWords.add("masturbate");
        badWords.add("menage a trois");
        badWords.add("milf");
        badWords.add("missionary position");
        badWords.add("motherfucker");
        badWords.add("mound of venus");
        badWords.add("mr hands");
        badWords.add("muff diver");
        badWords.add("muffdiving");
        badWords.add("nambla");
        badWords.add("nawashi");
        badWords.add("negro");
        badWords.add("neonazi");
        badWords.add("nigga");
        badWords.add("nigger");
        badWords.add("nig nog");
        badWords.add("nimphomania");
        badWords.add("nipple");
        badWords.add("nipples");
        badWords.add("nsfw images");
        badWords.add("nude");
        badWords.add("nudity");
        badWords.add("nympho");
        badWords.add("nymphomania");
        badWords.add("octopussy");
        badWords.add("omorashi");
        badWords.add("one cup two girls");
        badWords.add("one guy one jar");
        badWords.add("orgasm");
        badWords.add("paedophile");
        badWords.add("orgy");
        badWords.add("paki");
        badWords.add("pakistan");
        badWords.add("panties");
        badWords.add("panty");
        badWords.add("pedobear");
        badWords.add("pedophile");
        badWords.add("pegging");
        badWords.add("penis");
        badWords.add("phone sex");
        badWords.add("pissing");
        badWords.add("piece of shit");
        badWords.add("piss pig");
        badWords.add("pisspig");
        badWords.add("playboy");
        badWords.add("pleasure chest");
        badWords.add("pole smoker");
        badWords.add("ponyplay");
        badWords.add("poontang");
        badWords.add("poop chute");
        badWords.add("punany");
        badWords.add("porn");
        badWords.add("porno");
        badWords.add("pornography");
        badWords.add("prince albert piercing");
        badWords.add("pubes");
        badWords.add("pussy");
        badWords.add("queaf");
        badWords.add("queef");
        badWords.add("quim");
        badWords.add("raghead");
        badWords.add("raging boner");
        badWords.add("raping");
        badWords.add("rapist");
        badWords.add("rectum");
        badWords.add("reverse cowgirl");
        badWords.add("rimjob");
        badWords.add("rimming");
        badWords.add("rosy palm");
        badWords.add("rosy palm and her 5 sisters");
        badWords.add("rusty trombone");
        badWords.add("sadism");
        badWords.add("santorum");
        badWords.add("schlong");
        badWords.add("scissoring");
        badWords.add("semen");
        badWords.add("shaved beaver");
        badWords.add("shaved pussy");
        badWords.add("shemale");
        badWords.add("shibari");
        badWords.add("shit");
        badWords.add("shitblimp");
        badWords.add("shitty");
        badWords.add("shota");
        badWords.add("shrimping");
        badWords.add("skeet");
        badWords.add("slanteye");
        badWords.add("slut");
        badWords.add("s&m");
        badWords.add("smut");
        badWords.add("snatch");
        badWords.add("snowballing");
        badWords.add("sodomize");
        badWords.add("sodomy");
        badWords.add("splooge");
        badWords.add("splooge moose");
        badWords.add("spooge");
        badWords.add("spread legs");
        badWords.add("spunk");
        badWords.add("strap on");
        badWords.add("strapon");
        badWords.add("strappado");
        badWords.add("strip club");
        badWords.add("style doggy");
        badWords.add("suck");
        badWords.add("sucks");
        badWords.add("suicide girls");
        badWords.add("sultry women");
        badWords.add("swastika");
        badWords.add("swinger");
        badWords.add("tainted love");
        badWords.add("taste my");
        badWords.add("tea bagging");
        badWords.add("threesome");
        badWords.add("throating");
        badWords.add("tied up");
        badWords.add("tight white");
        badWords.add("tits");
        badWords.add("titties");
        badWords.add("titty");
        badWords.add("tongue in a");
        badWords.add("topless");
        badWords.add("tosser");
        badWords.add("towelhead");
        badWords.add("tranny");
        badWords.add("tribadism");
        badWords.add("tub girl");
        badWords.add("tubgirl");
        badWords.add("tushy");
        badWords.add("twat");
        badWords.add("twink");
        badWords.add("twinkie");
        badWords.add("two girls one cup");
        badWords.add("undressing");
        badWords.add("upskirt");
        badWords.add("urethra play");
        badWords.add("urophilia");
        badWords.add("vagina");
        badWords.add("venus mound");
        badWords.add("vibrator");
        badWords.add("violet wand");
        badWords.add("vorarephilia");
        badWords.add("voyeur");
        badWords.add("vulva");
        badWords.add("wank");
        badWords.add("wetback");
        badWords.add("wet dream");
        badWords.add("white power");
        badWords.add("wrinkled starfish");
        badWords.add("wrapping men");
        badWords.add("yaoi");
        badWords.add("yellow showers");
        badWords.add("yiffy");
        badWords.add("zoophilia");
        
    }

    
    public static boolean containsBadWords(String text) {
        String[] words = text.split("\\s+");

        for (String word : words) {
            if (badWords.contains(word.toLowerCase())) {
                return true;
            }
        }

        return false;
    }
    
	
    /*static Long2ObjectMap<String[]> words = new Long2ObjectOpenHashMap<>();
	static int largestWordLength = 0;

	public static void flag(String word) {
		String[] ignore_in_combination_with_words = new String[]{};
		if (word.length() > largestWordLength) {
			largestWordLength = word.length();
		}
		words.put(LongHashFunction.xx().hashChars(word.replaceAll(" ", "")), ignore_in_combination_with_words);
	}

	public static void loadConfigs() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("src/file/Word_Filter - Sheet1.csv"));
			String line = "";
			int counter = 0;
			while((line = reader.readLine()) != null) {
				counter++;
				String[] content = null;
				try {
					content = line.split(",");
					if(content.length == 0) {
						continue;
					}
					String word = content[0];
					String[] ignore_in_combination_with_words = new String[]{};
					if(content.length > 1) {
						ignore_in_combination_with_words = content[1].split("_");
					}

					if(word.length() > largestWordLength) {
						largestWordLength = word.length();
					}
					words.put(LongHashFunction.xx().hashChars(word.replace(" ", "")), ignore_in_combination_with_words);

				} catch(Exception e) {
					e.printStackTrace();
				}

			}
			System.out.println("Loaded " + counter + " words to filter out");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final char[][] convert = {
			{'o', '0'},
			{'i', '1'},
			{'l', '1'},
			{'t', '+'},
			{'e', '3'},
			{'i', '!'},
			{'l', '!'},
			{'s', '$'},
			{'a', '&'},
			{'a', '@'},
			{'c', '('},
			{'d', ')'},
			{'d', '0'},
			{'g', '6'},
			{'t', '7'},
			{'g', '9'},
			{'s', '5'},
			{'a', '4'}
	};

	private static final ThreadLocal<StringBuilder> sb = ThreadLocal.withInitial(StringBuilder::new); // make this regular if you don't need thread safety.

	public static boolean badWordsFound(String input) {
		if (input == null) {
			return false;
		}

		StringBuilder sb = BadWords.sb.get();
		sb.setLength(0);

		removeLeetspeak:
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (Character.isLetter(c)) {
				sb.append(Character.toLowerCase(c));
			} else {
				for (char[] conversion : convert) {
					if (c == conversion[1]) {
						sb.append(conversion[0]);
						continue removeLeetspeak;
					}
				}
			}
		}

		// iterate over each letter in the word
		for (int start = 0; start < sb.length(); start++) {
			// from each letter, keep going to find bad words until either the end of the sentence is reached, or the max word length is reached.
			for (int offset = 1; offset < (sb.length() + 1 - start) && offset < largestWordLength; offset++) {
				long hash = LongHashFunction.xx().hashChars(sb, start, start + offset);
				if (words.containsKey(hash)) {
					// for example, if you want to say the word bass, that should be possible.
					String[] ignoreCheck = words.get(hash);
					boolean ignore = false;
					for (int s = 0; s < ignoreCheck.length; s++) {
						if (indexOf(sb, ignoreCheck[s]) >= 0) {
							ignore = true;
							break;
						}
					}
					if (!ignore) {
						return true;
					}
				}
			}
		}

		return false;
	}

	private static int indexOf(CharSequence source, CharSequence target) {
		int sourceCount = source.length();
		int targetCount = target.length();
		int sourceOffset = 0;
		int targetOffset = 0;

		if (0 >= sourceCount) {
			return (targetCount == 0 ? sourceCount : -1);
		}
		if (targetCount == 0) {
			return 0;
		}

		char first = target.charAt(targetOffset);
		int max = sourceOffset + (sourceCount - targetCount);

		for (int i = sourceOffset; i <= max; i++) {
			
			if (source.charAt(i) != first) {
				while (++i <= max && source.charAt(i) != first);
			}

			
			if (i <= max) {
				int j = i + 1;
				int end = j + targetCount - 1;
				for (int k = targetOffset + 1; j < end && source.charAt(j)
						== target.charAt(k); j++, k++);

				if (j == end) {

					return i - sourceOffset;
				}
			}
		}
		return -1;
	}*/

}
	

    

