package security;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import org.jboss.resteasy.auth.oauth.OAuthConsumer;
import org.jboss.resteasy.auth.oauth.OAuthException;
import org.jboss.resteasy.auth.oauth.OAuthProvider;
import org.jboss.resteasy.auth.oauth.OAuthRequestToken;
import org.jboss.resteasy.auth.oauth.OAuthToken;

import server.management.ServerManager;

/**
 * 
 * @author Ryan Class to manage the tokens used in authenication
 */
public class OAuthManager implements OAuthProvider {
    private ArrayList<OAuthToken> tokens; // makes sense to store in memory
    private ArrayList<OAuthRequestToken> rTokens; // makes sense to store in memory
    private ArrayList<OAuthConsumer> users; // makes sense to read from a file

    public OAuthManager() {
        tokens = new ArrayList<OAuthToken>();
        rTokens = new ArrayList<OAuthRequestToken>();
        users = new ArrayList<OAuthConsumer>();
        // TODO populate users from a file rather than at instatntaiton
        try {
           /* try {
                BufferedReader r = new BufferedReader(new FileReader(new File(System.getProperty("catalina.base") + "/registeredApps")));
                String line = null;
                while ((line = r.readLine()) != null) {
                    ServerManager.log.info("Current Line: " + line);
                    String[] creds = line.split(",");
                    registerConsumer(creds[0],creds[1],creds[2]);
                }
                r.close();
            } catch (FileNotFoundException e) {
                ServerManager.log.error(getClass().getPackage().toString());
                ServerManager.log.error(e.getMessage());
                ServerManager.log.error("FILE NOT FOUND FOR Registered Clients");
            } catch (IOException e) {
            }*/
            //TODO a temporary measure while I configure the registered app file
            if(users.isEmpty()) {   
                registerConsumer("1234-5678-9101-1121", "p2plWebApp", "");
            }
        } catch (OAuthException e) {
            ServerManager.log.error("Unable to start OAuthManager " + e.getMessage());
        }
    }

    @Override
    public OAuthConsumer registerConsumer(String consumerKey, String displayName, String connectURI)
            throws OAuthException {
        String secret = makeRandomString();
        OAuthConsumer con = new OAuthConsumer(consumerKey, "1234-5678-9101-1121", displayName, connectURI);
        for (OAuthConsumer conCheck : users) {
            if (con.getKey() != conCheck.getKey()) {
                throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED,
                        "Unable to create consumer with key " + con.getKey());
            }
        }
        users.add(con);
        return con;
    }

    @Override
    public void registerConsumerScopes(String consumerKey, String[] scopes) throws OAuthException {
        OAuthConsumer consumer = this.getConsumer(consumerKey);
        consumer.setScopes(scopes);
    }

    @Override
    public void registerConsumerPermissions(String consumerKey, String[] permissions) throws OAuthException {
        OAuthConsumer consumer = this.getConsumer(consumerKey);
        consumer = new OAuthConsumer(consumer.getKey(), consumer.getSecret(), "?", consumer.getDisplayName(),
                permissions);
    }

    @Override
    public String getRealm() {
        return "p2plRealm";
    }

    @Override
    public OAuthConsumer getConsumer(String consumerKey) throws OAuthException {
        for (OAuthConsumer con : users) {
            if (con.getKey().equals(consumerKey)) {
                return con;
            }
        }
        throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED,
                "No consumer has been registered with that information");
    }

    @Override
    public OAuthRequestToken getRequestToken(String consumerKey, String requestToken) throws OAuthException {
        for (OAuthRequestToken rToken : rTokens) {
            if (consumerKey != null && rToken.getToken().equals(requestToken)
                    && rToken.getConsumer().getKey().equals(consumerKey)) {
                return rToken;
            }
        }
        throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED,
                "No Consumer with the provided key " + consumerKey);
    }

    @Override
    public OAuthToken getAccessToken(String consumerKey, String accessToken) throws OAuthException {
        ServerManager.log.info("getAccessToken");
        for (OAuthToken token : tokens) {
            ServerManager.log.info("cK: " + consumerKey + " token:" + token.getToken() + " aT " + accessToken
                    + " tokenConKey" + token.getConsumer().getKey());
            if (consumerKey != null && token.getToken().equals(accessToken)
                    && token.getConsumer().getKey().equals(consumerKey)) {
                OAuthToken retToken = new OAuthToken(token.getToken(), token.getSecret(),
                        token.getConsumer().getScopes(), token.getConsumer().getPermissions(), -1, token.getConsumer());
                return retToken;
            }
        }
        throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "No Consumer found with key " + consumerKey);
    }

    @Override
    public OAuthToken makeRequestToken(String consumerKey, String callback, String[] scopes, String[] permissions)
            throws OAuthException {
        if (consumerKey == null) {
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Request denied, Invalid Key supplied");
        }
        String secret = makeRandomString();
        String token = makeRandomString();
        for (OAuthRequestToken rToken : rTokens) {
            if (rToken.getConsumer().getKey().equals(consumerKey)) {
                throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED,
                        "Request Token for key " + consumerKey + " cannot be created");
            }
        }
        OAuthRequestToken retToken = new OAuthRequestToken(token, secret, callback, scopes, permissions, -1,
                getConsumer(consumerKey));
        rTokens.add(retToken);
        return retToken;
    }

    @Override
    public OAuthToken makeAccessToken(String consumerKey, String requestToken, String verifier) throws OAuthException {
        String token = makeRandomString();
        String secret = makeRandomString();
        OAuthToken reqToken = verifyAndRemoveRequestToken(consumerKey, requestToken, verifier);
       //Unneccessary check for access token being in the token list already, if the user logs twice in change thier token
        /* for (OAuthToken lToken : tokens) {
            if (lToken.getConsumer().getKey().equals(consumerKey) && reqToken.getToken().equals(requestToken)) {
                throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED,
                        "Access Token for " + consumerKey + " cannot be created");
            }
        }*/
        OAuthToken retToken = new OAuthToken(token, secret, reqToken.getPermissions(), reqToken.getScopes(), -1,
                reqToken.getConsumer());
        tokens.add(retToken);
        return retToken;
    }

    private OAuthRequestToken verifyAndRemoveRequestToken(String consumerKey, String requestToken, String verifier)
            throws OAuthException {
        OAuthRequestToken token = getRequestToken(consumerKey, requestToken);
        OAuthRequestToken returnToken = null;
        checkConsumerToken(token, consumerKey);
        if (verifier == null || !verifier.equals(token.getVerifier())) {
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Invalid verifier for token " + requestToken);
        }
        for (int i = 0; i < rTokens.size(); i++) {
            if (rTokens.get(i).getToken().equals(requestToken)) {
                returnToken = rTokens.get(i);
                rTokens.remove(i);
                break;
            }
        }
        return returnToken;
    }

    private void checkConsumerToken(OAuthToken token, String consumerKey) throws OAuthException {
        if (consumerKey != null && !consumerKey.equals(token.getConsumer().getKey())) {
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Invalid Consumer Key");
        }
        if (tokens.contains(token)) {
            throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED, "Unable to Register Token");
        }
    }

    @Override
    public String authoriseRequestToken(String consumerKey, String requestToken) throws OAuthException {
        String verifier = makeRandomString();
        for (OAuthRequestToken rToken : rTokens) {
            if (rToken.getConsumer().getKey().equals(consumerKey) && rToken.getToken().equals(requestToken)) {
                rToken.setVerifier(verifier);
                return verifier;
            }
        }
        throw new OAuthException(HttpURLConnection.HTTP_UNAUTHORIZED,
                "Request Token for the consumer with key " + consumerKey + " cannot be authorized");
    }

    @Override
    public void checkTimestamp(OAuthToken token, long timestamp) throws OAuthException {

        // TODO Left Unimplemented as for now timeout is not considered required
        // regarding security

    }

    @Override
    public Set<String> convertPermissionsToRoles(String[] permissions) {
        // TODO Auto-generated method stub
        return null;
    }

    public String makeRandomString() {
        String ranString = UUID.randomUUID().toString().replaceAll("-", "");
        return ranString;
    }

    public void RevokeToken(OAuthToken accessToken) {
        ServerManager.log.info("Removing token");
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).getToken().equals(accessToken.getToken())) {
                tokens.remove(i);
            }
        }
    }

}
