package br.com.senac.ccs.lpc;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

@Controller
@RequestMapping(value="/xavierchat/*", produces={"application/json"})
public class ChatController {

    @Autowired
    private Chat chat;

    @RequestMapping(value="/join", method=RequestMethod.GET)
    public @ResponseBody Result join(@Valid User user, HttpSession session) {
        String id = session.getId();
        DeferredResult<Result> deferredResult = new DeferredResult<Result>();
        Screen screen = new WebScreen(deferredResult);
        return chat.join(id, user.getName(), screen);
    }

    @RequestMapping(value="/sendchat", method=RequestMethod.GET)
    public @ResponseBody void sendchat(@RequestParam String message, HttpSession session) {
        chat.sendchat(session.getId(), message);
    }

    @RequestMapping(value="/bind", method=RequestMethod.GET)    
    public @ResponseBody DeferredResult<Result> bind( HttpSession session ) {
        DeferredResult<Result> deferredResult = new DeferredResult<Result>();
        Screen screen = new WebScreen(deferredResult);
        chat.bind(session.getId(), screen);
        return deferredResult;
    }

}
