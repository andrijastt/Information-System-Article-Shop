/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.ac.bg.etf.is1.server.resources;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import rs.ac.bg.etf.is1.commands.ChangeArticlePriceCommand;
import rs.ac.bg.etf.is1.commands.CreateArticleCommand;
import rs.ac.bg.etf.is1.commands.GetAllArticlesThatUserSellsCommand;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.rest.ArticleRest;
import rs.ac.bg.etf.is1.server.JMSCommunicator;

/**
 *
 * @author stoja
 */
@Path("article")
public class ArticleResource {
    
    @Inject
    JMSCommunicator comm;
    
    @PersistenceContext(name = "prodavnicaArtikalaPU")
    EntityManager em;
    
    @POST
    @Path("create")
    public Response createArticle(@FormParam("name") String name, @FormParam("description") String description, @FormParam("price") String price
        , @FormParam("IDUser") String IDUser, @FormParam("IDCategory") String IDCategory){        
        CreateArticleCommand cac = new CreateArticleCommand(name, description, price, 0, IDUser, IDCategory);
        JMSResponse response = comm.exchange(cac);
        if(response instanceof SuccessfulResponse){
            SuccessfulResponse sr = (SuccessfulResponse) response;
            return Response.status(Response.Status.OK).entity(sr.toString()).build();
        }
        FailedResponse fr = (FailedResponse) response;
        return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
    }
    
        @POST
        @Path("changeArticlePrice")
        public Response changeArticlePrice(@FormParam("IDUser") String IDUser, @FormParam("IDArticle") String IDArticle, @FormParam("price") String price){
            ChangeArticlePriceCommand capc = new ChangeArticlePriceCommand(price, IDUser, IDArticle);
            JMSResponse response = comm.exchange(capc);
            if(response instanceof SuccessfulResponse){
                SuccessfulResponse sr = (SuccessfulResponse) response;
                return Response.status(Response.Status.OK).entity(sr.toString()).build();
            }
            FailedResponse fr = (FailedResponse) response;
            return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
        }
        
        @GET
        @Path("getAllArticlesThatUserSells/{IDUser}")
        public Response getAllArticlesThatUserSells(@PathParam("IDUser") String IDUser){
            GetAllArticlesThatUserSellsCommand gaatusc = new GetAllArticlesThatUserSellsCommand(IDUser);
            JMSResponse response = comm.exchange(gaatusc);
            if(response instanceof DataResponse){
                DataResponse<List<Article>> dataresp = (DataResponse<List<Article>>) response;
                List<ArticleRest> articles = new ArrayList<>();
                for(Article article: dataresp.getData()){
                    ArticleRest articleRest = new ArticleRest(article.getIDArticle(), article.getName(), article.getDescription()
                            , article.getPrice(), article.getDiscount(), article.getIDUser().getIDUser(), article.getIDCategory().getIDCategory());
                    articles.add(articleRest);
                }
                return Response.status(Response.Status.OK).entity(articles).build();
            }
            FailedResponse fr = (FailedResponse) response;
            return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
        }
    
}
