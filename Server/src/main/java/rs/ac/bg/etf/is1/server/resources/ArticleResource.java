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
import rs.ac.bg.etf.is1.commands.AddArticleAmountInCartCommand;
import rs.ac.bg.etf.is1.commands.ChangeArticlePriceCommand;
import rs.ac.bg.etf.is1.commands.ChangeDiscountForArticleCommand;
import rs.ac.bg.etf.is1.commands.CreateArticleCommand;
import rs.ac.bg.etf.is1.commands.GetAllArticlesThatUserSellsCommand;
import rs.ac.bg.etf.is1.commands.GetAllItemsInCartForUserCommand;
import rs.ac.bg.etf.is1.commands.RemoveArticleAmountInCartCommand;
import rs.ac.bg.etf.is1.entities.Article;
import rs.ac.bg.etf.is1.entities.Incart;
import rs.ac.bg.etf.is1.responses.DataResponse;
import rs.ac.bg.etf.is1.responses.FailedResponse;
import rs.ac.bg.etf.is1.responses.JMSResponse;
import rs.ac.bg.etf.is1.responses.SuccessfulResponse;
import rs.ac.bg.etf.is1.rest.ArticleRest;
import rs.ac.bg.etf.is1.rest.InCartRest;
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
        
        @POST
        @Path("changeDiscountForArticle")
        public Response changeDiscountForArticle(@FormParam("IDUser") String IDUser, @FormParam("IDArticle") String IDArticle, @FormParam("discount") String discount){
            ChangeDiscountForArticleCommand cdfac = new ChangeDiscountForArticleCommand(discount, IDUser, IDArticle);
            JMSResponse response = comm.exchange(cdfac);
            if(response instanceof SuccessfulResponse){
                SuccessfulResponse sr = (SuccessfulResponse) response;
                return Response.status(Response.Status.OK).entity(sr.toString()).build();
            }
            FailedResponse fr = (FailedResponse) response;
            return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
        }
        
        @POST
        @Path("addArticleAmountInCart")
        public Response addArticleAmountInCart(@FormParam("IDUser") String IDUser, @FormParam("IDArticle") String IDArticle, @FormParam("amount") String amount){
            AddArticleAmountInCartCommand aaaicc = new AddArticleAmountInCartCommand(IDUser, amount, IDArticle);
            JMSResponse response = comm.exchange(aaaicc);
            if(response instanceof SuccessfulResponse){
                SuccessfulResponse sr = (SuccessfulResponse) response;
                return Response.status(Response.Status.OK).entity(sr.toString()).build();
            }
            FailedResponse fr = (FailedResponse) response;
            return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
        }
        
        @POST
        @Path("removeArticleAmountInCart")
        public Response removeArticleAmountInCart(@FormParam("IDUser") String IDUser, @FormParam("IDArticle") String IDArticle, @FormParam("amount") String amount){
            RemoveArticleAmountInCartCommand raaaicc = new RemoveArticleAmountInCartCommand(IDUser, IDArticle, amount);
            JMSResponse response = comm.exchange(raaaicc);
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
        
        @GET
        @Path("getAllItemsInCartForUser/{IDUser}")
        public Response getAllItemsInCartForUser(@PathParam("IDUser") String IDUser){
            GetAllItemsInCartForUserCommand gaatusc = new GetAllItemsInCartForUserCommand(IDUser);
            JMSResponse response = comm.exchange(gaatusc);
            if(response instanceof DataResponse){
                DataResponse<List<Incart>> dataresp = (DataResponse<List<Incart>>) response;
                List<InCartRest> articles = new ArrayList<>();
                for(Incart article: dataresp.getData()){
//                    ArticleRest articleRest = new ArticleRest(article.getIDArticle(), article.getName(), article.getDescription()
//                            , article.getPrice(), article.getDiscount(), article.getIDUser().getIDUser(), article.getIDCategory().getIDCategory());
//                    articles.add(articleRest);
                    InCartRest icr = new InCartRest(article.getIncartPK().getIDUser(), article.getIncartPK().getIDArticle(), article.getAmount());
                    articles.add(icr);
                }
                return Response.status(Response.Status.OK).entity(articles).build();
            }
            FailedResponse fr = (FailedResponse) response;
            return Response.status(Response.Status.BAD_REQUEST).entity(fr.toString()).build();
        }
    
}
