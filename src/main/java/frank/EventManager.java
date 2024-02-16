package frank;

import commands.list.Hlfrench;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMuteEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class EventManager extends ListenerAdapter {


//    Set<Long> shid = new HashSet<Long>();

//    class Helper extends TimerTask {
//        List<TextChannel> tc;
//        Helper(List<TextChannel> textChannels) {
//            tc = textChannels;
//        }
//        public void run() {
//            for (TextChannel channel : tc) {
//                channel.sendTyping().queue();
//            }
//        }
//    }


    @Override
    public void onGuildVoiceMute(GuildVoiceMuteEvent event) {
        if (event.getMember().getId().equals(Run.ownerid)) {
            event.getGuild().mute(event.getMember(), false).queue();
        }
    }


    @Override
    public void onReady(ReadyEvent event) {




//                Timer timer = new Timer();
//
//                // Helper class extends TimerTask
//                TimerTask task = new Helper( event.getJDA().getGuildById("####").getTextChannels());
//
//                /*
//                 *  Schedule() method calls for timer class.
//                 *  void schedule(TimerTask task, Date firstTime, long period)
//                 */
//
//                timer.schedule(task, 1, 10100);
//
//        }

//        while (true) {
//            Member m = event.getJDA().getGuildById("####").getMemberById("####");
////            System.out.println(m.getOnlineStatus());
//            if (!m.getOnlineStatus().equals(OnlineStatus.OFFLINE)) {
//                event.getJDA().getGuildById("####").getTextChannelById("####").sendMessage("ANDY IS ONLINE <@####>").queue();
//            }
        }










        // make pinned message embed
    // make it unspammable

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
//            System.out.println(event.getReactionEmote());
        Message msg = event.retrieveMessage().complete();





/*
    Code Example https://ci.dv8tion.net/job/JDA/javadoc/net/dv8tion/jda/api/requests/restaction/pagination/ReactionPaginationAction.html

 // Remove reactions for the specified emoji
 public static void removeReaction(Message message, String emoji) {
     // get paginator
     ReactionPaginationAction users = message.retrieveReactionUsers(emoji);
     // remove reaction for every user
     users.forEachAsync((user) ->
         message.removeReaction(emoji, user).queue()
     );
 }

// custom
message.addReaction("minn:245267426227388416").queue();
// unicode escape
message.addReaction("\uD83D\uDE02").queue();
// codepoint notation
message.addReaction("U+1F602").queue();

 */

//        event.getChannel().sendMessage(event.getReactionEmote().getName()).queue();
//        event.getChannel().sendMessage("" + msg).queue();

//        List<User> alreadyReacted = new ArrayList<>();
//
//        for (MessageReaction r : msg.getReactions()) {
//            MessageReaction.ReactionEmote emote = r.getReactionEmote();
//
////            System.out.println(emote.getEmoji());
//
//
//            try {
//                boolean isPin = false;
//
//                for (String pins : Run.pinEmotes)
//                    if (pins.equals(emote.getEmoji()))
//                        isPin = true;
//
//            if (!isPin) continue;
//
//
//                for (User u : msg.retrieveReactionUsers(emote.getEmoji()).complete())
//                    if (!alreadyReacted.contains(u))
//                        alreadyReacted.add(u);
//
//            } catch(IllegalStateException e) {
//            }
//        }
//
//        System.out.println(alreadyReacted.size());
//
//
//
//        if (alreadyReacted.size() >= 2) {
//            event.getGuild().getTextChannelById(Run.channelid).sendMessage(msg.getContentRaw()).queue();
//        }

        //thing explain(r)?? ?
//        int n = 0;
//        for (MessageReaction r : msg.getReactions()) {
//            n += msg.retrieveReactionUsers(r.getReactionEmote() + "").complete().size();
////
////            users.forEachAsync((user) -> {
////                n.getAndIncrement();
////                return false;
////            });
//        }
//
//        event.getChannel().sendMessage(""+n).queue();

    }





    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

//    System.out.println(event.getMessage().getContentRaw());

        String userid = event.getAuthor().getId();
        String sqid = Run.db.getSQID(userid);

        if (Hlfrench.hlfrenchParticipants.containsKey(userid)) {
            String correct = Hlfrench.hlfrenchParticipants.get(userid).getCorrect();
            String response;



            if (event.getMessage().getContentRaw().equals(correct)) {
                response = ":white_check_mark: Vous pensez, donc vous avez raison.";
                try {
                    Run.db.setItemQuantity(sqid, Run.db.conjugationCurrency, Run.db.conjugationGain + Run.db.getItemQuantity(sqid, Run.db.conjugationCurrency));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                response = ":negative_squared_cross_mark: IB Niveau 1 - Bonne Réponse: "+correct;
                try {
                    Run.db.setItemQuantity(sqid, Run.db.conjugationCurrency, - Run.db.conjugationLoss + Run.db.getItemQuantity(sqid, Run.db.conjugationCurrency));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            Hlfrench.regenPrompt(userid, event.getChannel(), response);
        }

        String s = event.getMessage().getContentRaw();
        for (String word : s.split(" ")) {
            if (Run.swear.swear.contains(word)) {
                event.getMessage().reply("-10000 social credits").queue();
                try {
                    Run.db.setItemQuantity(sqid, Run.db.swearcCurrency, - Run.db.swearLoss + Run.db.getItemQuantity(sqid, Run.db.conjugationCurrency));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

//        if (event.isFromGuild() == true) return;
//
//        if (event.getMessage())


//           if (ConstoleFrame.LoggingEnabled)
//               ConsoleFrame.console.append("\n"+event.getMessage().getContentRaw());
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        String command = event.getName();

//        execute(event);

//        if (main.Run.commandManagerTreeMap.containsKey(command) && ConsoleFrame.SlashCommandsEnabled) {
        if (Run.commandManagerTreeMap.containsKey(command)) {
            try {
                Run.commandManagerTreeMap.get(command).execute(event);
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

    }


}
