import NavBar from './nav/NavBar';
import { Route, Routes, useNavigate} from "react-router-dom";
import Consent from './consent/Consent';
import Scopes from './scopes/Scopes';
import Home from './home/Home';
import ReadyCheckRoute from './ready/ReadyCheckRoute';
import { AuthProvider } from './auth/AuthProvider';
import SignIn from './auth/SignIn';
import Layout from './layout/Layout';
import Profile from './graph/Profile';
import InteractionRequired from './auth/InteractionRequired';
import { TenantAlias } from './auth/TenantAlias';

function App() {

  const apis = [{endpoint: "https://graph.microsoft.com"}];
  const navigate = useNavigate();
  const clientId = 'b5c2e510-4a17-4feb-b219-e55aa5b74144'; //Lab App Id
  const redirectUri = 'http://localhost:3000/auth/client-redirect'; //Lab App 
  const tenantAlias: TenantAlias = "organizations";

  return (
    <>
    <AuthProvider clientId={clientId} redirectUri={redirectUri} navigate={navigate} tenantAlias={tenantAlias} apis={apis}>
    <NavBar></NavBar>
    <Layout>
    <Routes>
      <Route path='*' element={<Home />} />
      <Route path="/" element={<Home />} />
      <Route path="/signin" element={<SignIn />} />
      <Route path="/consent" element={<Consent />} />
      <Route element={<ReadyCheckRoute />}>
        <Route path="/scopes" element={<Scopes />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/interactionrequired" element={<InteractionRequired/>}/>
      </Route>
      
    </Routes>
    </Layout>
    </AuthProvider>
    </>
  );
}

export default App;
