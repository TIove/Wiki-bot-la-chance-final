import pymongo
import os

mongo_client = pymongo.MongoClient(os.environ.get('MONGO_DB', 'mongodb://localhost:27017/'))
mongo_db = mongo_client['mydatabase']
mongo_col = mongo_db['access_tokens']


# function to get access token by user_id
async def get_access_token(user_id):
    token = mongo_col.find_one({"user_id": user_id})
    if token:
        return token['access_token']
    else:
        return None


async def get_headers(user_id):
    access_token = await get_access_token(user_id)
    if not access_token:
        return None
    return {"Authorization": f"Bearer {access_token}"}
