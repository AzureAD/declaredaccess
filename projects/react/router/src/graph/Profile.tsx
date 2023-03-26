import React, { useEffect, useState } from 'react';
import { useAuthContext } from 'declaredaccesslib';


function Profile(){

    const {apiClient} = useAuthContext();
    const [profileJson, setProfileJson] = useState<string>("");

    useEffect(()=> {

        setProfileJson("");

        async function getProfile(){
            
            try {

                if(apiClient === undefined){
                    throw new Error("unexpected: apiClient undefined")
                }

                const result = await apiClient?.fetch(
                    'https://graph.microsoft.com/v1.0/me',
                );
                
                if(result.ok){
                    const data = await result.text();
                    setProfileJson(data);
                }else{
                    //Handle whatever errors could happen that have nothing to do with identity
                    console.log(result);
                }
            }catch(e){ 
                console.log(e);
            }
        }

        
        getProfile().catch((e)=> {console.log(e);});

    }, [apiClient]);
    
    return (
        <>
        <h1 className="inline-block text-2xl sm:text-3xl font-extrabold text-slate-900 tracking-tight dark:text-slate-200">Profile as JSON</h1>
        <p className='text-slate-900 tracking-tight dark:text-slate-200'>{profileJson}</p>
        </>
    )
}

export default Profile;