import NavBar from './nav/NavBar';
import { Route, Routes} from "react-router-dom";
import Consent from './consent/Consent';
import Scopes from './scopes/Scopes';
import Home from './home/Home';
import ReadyCheckRoute from './ready/ReadyCheckRoute';
import { AuthProvider } from './auth/AuthProvider';
import SignIn from './auth/SignIn';
import Layout from './layout/Layout';
import Profile from './graph/Profile';

function App() {

  return (
    <>
    <AuthProvider clientId={'c8263c9e-3bd0-4ee6-af0c-799099b8ec56'} redirectUri={'http://localhost:3000'}>
    <NavBar></NavBar>
    <Layout>
    <Routes>
      
      <Route path="/" element={<Home />} />
      <Route path="/signin" element={<SignIn />} />
      <Route path="/consent" element={<Consent />} />
      <Route element={<ReadyCheckRoute />}>
        <Route path="/scopes" element={<Scopes />} />
        <Route path="/profile" element={<Profile />} />
      </Route>
      
    </Routes>
    </Layout>
    </AuthProvider>
    </>
  );
}

export default App;
