package com.maher.nowhere.helpers;


import com.maher.nowhere.model.Comment;
import com.maher.nowhere.model.Feedback;
import com.maher.nowhere.model.Film;
import com.maher.nowhere.model.Menu;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Pack;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.Reservation;
import com.maher.nowhere.model.SubMenu;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Utiles;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;


public class JsonToObjectParser {


    public User parseUser(JSONObject user) {
        JSONObject userJson = new JSONObject();
        User user1 = new User();

        try {
            userJson = new JSONObject(user.getJSONObject("user").toString());
        } catch (JSONException e) {
            e.printStackTrace();
            userJson = user;
            //return new User();
        }
        try {
            user1.setName(userJson.getString("username"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user1.setEmail(userJson.getString("email"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user1.setPassword(userJson.getString("password"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            user1.setImage(userJson.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user1.setCoverPhoto(userJson.getString("photo_couverture"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            user1.setId(userJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return user1;

    }

    public ArrayList<User> parseUsers(JSONArray usersJson) {
        ArrayList<User> users = new ArrayList<>();
        for (int i = 0; i < usersJson.length(); i++) {
            try {
                User user = parseUser(usersJson.getJSONObject(i));
                users.add(user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    private Feedback parseFeedback(JSONObject jsonObject) {
        Feedback feedback = new Feedback();


        try {
            feedback.setContenu(jsonObject.getString("contenue"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            feedback.setUserNote(jsonObject.getString("note"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            User user = parseUser(jsonObject.getJSONObject("_mobile_user"));
            feedback.setUser(user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            feedback.setcDate(new Utiles().parseDate2(jsonObject.getString("created_at")));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return feedback;


    }

    public ArrayList<Feedback> parseFeedbacks(JSONObject feedbackJson1) {

        JSONArray feedbackJson = null;
        try {
            feedbackJson = feedbackJson1.getJSONArray("feedback");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Feedback> feedbacks = new ArrayList<>();
        for (int i = 0; i < feedbackJson.length(); i++) {
            try {
                Feedback feedback = parseFeedback(feedbackJson.getJSONObject(i));
                try {
                    feedback.setGlobalNote(feedbackJson1.getString("Note"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    feedback.setGlobalNote("0");
                }
                feedbacks.add(feedback);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return feedbacks;
    }

    private Post parsePost(JSONObject postJson1) {
        Post post = new Post();


        JSONObject postJson = null;
        try {
            postJson = postJson1.getJSONObject("_event");
        } catch (JSONException e) {
            e.printStackTrace();
            postJson = postJson1;
        }
        try {
            post.setTitle(postJson.getString("_groupe_nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setId(postJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setName(postJson.getString("_evenement_nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setDescription(postJson.getString("descreption"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setUrlImage(postJson.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setNbrPlace(postJson.getInt("nbr_place"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setHeureDebut(postJson.getString("heure_debut"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setHeureFin(postJson.getString("heure_fin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            post.setDate(new Utiles().parseDate(postJson.getString("date")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            Owner owner = parseOwner(postJson.getJSONObject("prestataire"));
            post.setOwner(owner);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            post.setId(postJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return post;

    }

    private Comment parseComment(JSONObject commentJson) {
        Comment comment = new Comment();

        try {
            comment.setId(commentJson.getInt("id"));
            comment.setContenus(commentJson.getString("contenue"));
            comment.setDate(commentJson.getString("created_at"));
            comment.setcDate(new Utiles().parseDate2(commentJson.getString("created_at")));
            JSONObject owner = commentJson.getJSONObject("_mobile_user");
            comment.setOwnerId(owner.getInt("id"));
            comment.setOwnerImage(owner.getString("photo"));
            comment.setOwnerName(owner.getString("username"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return comment;

    }

    private Reservation parseReservation(JSONObject reservationJson) {
        Reservation reservation = new Reservation();
        try {
            reservation.setResNum(String.format(Locale.FRANCE, "%d", reservationJson.getInt("id")));
            reservation.setStatus(reservationJson.getBoolean("etat"));
            reservation.setDate(reservationJson.getString("created_at"));
        } catch (JSONException e) {
            e.printStackTrace();
            return reservation;
        }
        return reservation;

    }

    public ArrayList<Reservation> parseReservations(JSONArray reservationJson) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < reservationJson.length(); i++) {
            try {
                Reservation reservation = parseReservation(reservationJson.getJSONObject(i));
                reservations.add(reservation);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return reservations;
    }

    private Publication parsePublication(JSONObject publicationJson) {
        Publication publication = new Publication();

        try {
            publication.setId(publicationJson.getInt("id"));
            publication.setDescription(publicationJson.getString("description"));
            publication.setcDate(new Utiles().parseDate2(publicationJson.getString("created_at")));

            publication.setDate(publicationJson.getString("created_at"));
            publication.setImage(publicationJson.getString("image"));
            publication.setNbrJaime(publicationJson.getInt("nbr_jaime"));

            JSONObject owner = publicationJson.getJSONObject("_mobile_user");
            publication.setOwnerId(owner.getInt("id"));
            publication.setOwnerImage(owner.getString("photo"));
            publication.setOwnerName(owner.getString("username"));


            JSONArray jsonComments = publicationJson.getJSONArray("_commentaire_list");
            publication.setComments(parseComments(jsonComments));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return publication;

    }

    public ArrayList<Publication> parsePublications(JSONArray publicationJson) {
        ArrayList<Publication> publications = new ArrayList<>();
        for (int i = 0; i < publicationJson.length(); i++) {
            try {
                Publication publication = parsePublication(publicationJson.getJSONObject(i));
                publications.add(publication);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return publications;
    }

    public ArrayList<Comment> parseComments(JSONArray commentsJson) {
        ArrayList<Comment> comments = new ArrayList<>();
        for (int i = 0; i < commentsJson.length(); i++) {
            try {
                Comment comment = parseComment(commentsJson.getJSONObject(i));
                comments.add(comment);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return comments;
    }

    public ArrayList<Post> parsePosts(JSONArray postsJson) {
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < postsJson.length(); i++) {
            try {
                Post post = parsePost(postsJson.getJSONObject(i));
                posts.add(post);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return posts;
    }

    public ArrayList<Owner> parsePrestaire(JSONArray postsJson) {
        ArrayList<Owner> owners = new ArrayList<>();
        for (int i = 0; i < postsJson.length(); i++) {
            try {
                Owner owner = parseOwner(postsJson.getJSONObject(i));
                owners.add(owner);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return owners;
    }

    private Owner parseOwner(JSONObject ownerJson) {
        Owner owner = new Owner();
        try {
            owner.setGouvernorat(ownerJson.getString("gouvernorat"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setAdresse(ownerJson.getString("adresse"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setDescription(ownerJson.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setNom(ownerJson.getString("nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setTelFix(ownerJson.getString("tel_fix"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setTelMobile(ownerJson.getString("tel_mobile"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setLongitude(ownerJson.getDouble("longetude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setLatitude(ownerJson.getDouble("latitude"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setUrlImage(ownerJson.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            owner.setHeure_fermeture(ownerJson.getString("heure_fermeture"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            owner.setHeure_overture(ownerJson.getString("heure_overture"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setCouverture(ownerJson.getString("couverture"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setValide(ownerJson.getBoolean("valide"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            owner.setMot_cles(ownerJson.getString("mot_cles"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            owner.setBannee(ownerJson.getBoolean("bannee"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            owner.setId(ownerJson.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return owner;

    }

    public ArrayList<Menu> parseMenus(JSONArray jsonArray) {
        ArrayList<Menu> menus = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                Menu menu = new Menu();
                try {
                    menu.setNom(jsonArray.getJSONObject(i).getString("nom"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                menu.setSubMenus(parseSubMenus(jsonArray.getJSONObject(i).getJSONArray("_menu_list")));

                menus.add(menu);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return menus;
    }

    private ArrayList<SubMenu> parseSubMenus(JSONArray jsonArray) {
        ArrayList<SubMenu> subMenus = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                SubMenu subMenu = new SubMenu();
                JSONObject jsonObject = jsonArray.getJSONObject(i);


                try {
                    subMenu.setNom(jsonObject.getString("nom"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    subMenu.setPrix(jsonObject.getString("prix"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    subMenu.setDescription(jsonObject.getString("description"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                subMenus.add(subMenu);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return subMenus;
    }

    public ArrayList<Photo> parsePhotos(JSONObject photoJson) {
        JSONArray jsonArray = new JSONArray();
        ArrayList<Photo> photos = new ArrayList<>();

        try {
            jsonArray = photoJson.getJSONArray("Photos");
        } catch (JSONException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }


        for (int i = 0; i < jsonArray.length(); i++) {

            Photo photo = new Photo();
            try {
                photo.setUrl(jsonArray.getJSONObject(i).getString("photo"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                photo.setDescription(jsonArray.getJSONObject(i).getString("descreption"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

            photos.add(photo);

        }


        return photos;

    }

    private Film parseFilm(JSONObject jsonFilm) {

        Film film=new Film();


        try {
            film.setNom(jsonFilm.getString("_nom_film"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setNom(jsonFilm.getString("_nom_film"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setDuree(jsonFilm.getString("duree"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setDateSortie(jsonFilm.getString("date_sortie"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setRealisateur(jsonFilm.getString("realisateur"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setActeurs(jsonFilm.getString("acteurs"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setGenre(jsonFilm.getString("genre"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setNationalites(jsonFilm.getString("nationalites"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setSynopsis(jsonFilm.getString("synopsis"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setDateDebut(jsonFilm.getString("date_debut"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setDateFin(jsonFilm.getString("date_fin"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setHeureDefusin(jsonFilm.getString("_heure_diffusion_1")+" "+
                    jsonFilm.getString("_heure_diffusion_2")+" "+
                    jsonFilm.getString("_heure_diffusion_3"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            film.setVideo(jsonFilm.getString("_u_r_l"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setPhoto(jsonFilm.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            film.setOwner(parseOwner(jsonFilm.getJSONObject("prestataire")));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return film;

    }

    public ArrayList<Film> parseFilms(JSONArray jsonArray) {
        ArrayList<Film> films = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

                try {
                    Film film = parseFilm(jsonArray.getJSONObject(i));
                    films.add(film);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }
        return films;
    }

    private Product parseProduit(JSONObject jsonProduit) {

        Product product=new Product();


        try {
            product.setNom(jsonProduit.getString("_produit_nom"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            product.setDescription(jsonProduit.getString("description"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            product.setPrix(jsonProduit.getString("prix"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            product.setImg(jsonProduit.getString("photo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return product;

    }
    public ArrayList<Product> parseProduits(JSONArray jsonArray) {
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

            try {
                Product product = parseProduit(jsonArray.getJSONObject(i));
                products.add(product);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
    private Pack parsePack(JSONObject jsonObject) {

        Pack pack=new Pack();


        try {
            pack.setNom(jsonObject.getString("_nom_pack"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            pack.setType1(jsonObject.getString("_prix_pack_3"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pack.setType2(jsonObject.getString("_prix_pack_6"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pack.setType3(jsonObject.getString("_prix_pack_9"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pack.setType4(jsonObject.getString("_prix_pack_12"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            JSONArray jsonArray=jsonObject.getJSONArray("detail");
            ArrayList<String> details=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                details.add(jsonArray.getString(i));
            }
            pack.setDetails(details);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            pack.setId(jsonObject.getInt("id"));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return pack;

    }

    public ArrayList<Pack> parsePacks(JSONArray jsonArray) {
    ArrayList<Pack> packs = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {

        try {
            Pack pack = parsePack(jsonArray.getJSONObject(i));
            packs.add(pack);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
        return packs;
}

}
